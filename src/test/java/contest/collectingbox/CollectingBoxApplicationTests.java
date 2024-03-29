package contest.collectingbox;

import com.querydsl.jpa.impl.JPAQueryFactory;
import contest.collectingbox.entity.QTestEntity;
import contest.collectingbox.entity.TestEntity;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CollectingBoxApplicationTests {
    @Autowired
    EntityManager em;
    @Test
    void test(){
        TestEntity testEntity = new TestEntity();
        em.persist(testEntity);

        JPAQueryFactory query = new JPAQueryFactory(em);

        QTestEntity qTestEntity = QTestEntity.testEntity;
        TestEntity result = query.selectFrom(qTestEntity).fetchOne();

        Assertions.assertThat(result).isEqualTo(testEntity);

    }

}