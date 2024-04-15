package contest.collectingbox.module.collectingbox.domain;

import static contest.collectingbox.module.collectingbox.domain.QCollectingBox.collectingBox;
import static contest.collectingbox.module.location.domain.QLocation.location;

import com.querydsl.jpa.impl.JPAQueryFactory;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.QCollectingBoxDetailResponse;
import jakarta.persistence.EntityManager;

public class CollectingBoxRepositoryImpl implements CollectingBoxRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public CollectingBoxRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public CollectingBoxDetailResponse findDetailById(Long id) {

        return queryFactory
                .select(new QCollectingBoxDetailResponse(location.name, location.address.roadName,
                        location.address.streetNum,
                        collectingBox.updatedAt.stringValue(), collectingBox.tag.stringValue()))
                .from(collectingBox)
                .join(collectingBox.location, location)
                .where(collectingBox.id.eq(id))
                .fetchOne();

    }
}
