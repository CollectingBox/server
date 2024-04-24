package contest.collectingbox.module.collectingbox.domain;

import static contest.collectingbox.module.collectingbox.domain.QCollectingBox.collectingBox;
import static contest.collectingbox.module.location.domain.QLocation.location;
import static contest.collectingbox.module.review.domain.QReview.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.QCollectingBoxDetailResponse;
import contest.collectingbox.module.review.dto.ReviewResponse;
import contest.collectingbox.module.review.dto.QReviewResponse;
import jakarta.persistence.EntityManager;
import java.util.List;

public class CollectingBoxRepositoryImpl implements CollectingBoxRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public CollectingBoxRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public CollectingBoxDetailResponse findDetailById(Long id) {

        CollectingBoxDetailResponse response = queryFactory.select(new QCollectingBoxDetailResponse(
                        location.point,
                        location.name, location.address.roadName,
                        location.address.streetNum,
                        collectingBox.updatedAt.stringValue(), collectingBox.tag
                ))
                .from(collectingBox)
                .join(collectingBox.location, location)
                .where(collectingBox.id.eq(id))
                .fetchOne();

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
