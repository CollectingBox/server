package contest.collectingbox.module.collectingbox.domain.repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.spatial.locationtech.jts.JTSGeometryExpressions;
import contest.collectingbox.global.exception.CollectingBoxException;
import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.Tags;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import contest.collectingbox.module.collectingbox.dto.QCollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.QCollectingBoxResponse;
import contest.collectingbox.module.location.domain.GeoPoint;
import contest.collectingbox.module.review.dto.QReviewResponse;
import contest.collectingbox.module.review.dto.ReviewResponse;
import jakarta.persistence.EntityManager;
import org.locationtech.jts.geom.Point;

import java.util.List;

import static contest.collectingbox.global.exception.ErrorCode.NOT_FOUND_COLLECTING_BOX;
import static contest.collectingbox.module.collectingbox.domain.QCollectingBox.collectingBox;
import static contest.collectingbox.module.location.domain.QLocation.location;
import static contest.collectingbox.module.review.domain.QReview.review;

public class CollectingBoxRepositoryImpl implements CollectingBoxRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public CollectingBoxRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<CollectingBoxResponse> findAllWithinArea(GeoPoint center, int radius, Tags tags) {
        Point centerPoint = GeometryUtil.toPoint(center.getLongitude(), center.getLatitude());
        return queryFactory
                .select(new QCollectingBoxResponse(
                        collectingBox.id,
                        Expressions.stringTemplate("function('st_longitude', {0})", location.point)
                                .castToNum(double.class),
                        Expressions.stringTemplate("function('st_latitude', {0})", location.point)
                                .castToNum(double.class),
                        collectingBox.tag
                ))
                .from(collectingBox)
                .join(collectingBox.location, location)
                .where(JTSGeometryExpressions.asJTSGeometry(centerPoint).buffer(radius).contains(location.point),
                        collectingBox.tag.in(tags.getTags()))
                .fetch();
    }

    @Override
    public CollectingBoxDetailResponse findDetailById(Long id) {
        CollectingBoxDetailResponse response = queryFactory.select(new QCollectingBoxDetailResponse(
                        location.point,
                        location.name, location.roadName,
                        location.streetNum,
                        collectingBox.updatedAt.stringValue(), collectingBox.tag
                ))
                .from(collectingBox)
                .join(collectingBox.location, location)
                .where(collectingBox.id.eq(id))
                .fetchOne();

        if (response == null) {
            throw new CollectingBoxException(NOT_FOUND_COLLECTING_BOX);
        }

        List<ReviewResponse> reviews = queryFactory
                .select(new QReviewResponse(review.tag.stringValue(), review.createdAt.stringValue()))
                .from(review)
                .where(review.collectingBox.id.eq(id))
                .orderBy(review.createdAt.desc())
                .limit(10)
                .fetch();

        response.setReviews(reviews);

        return response;
    }
}
