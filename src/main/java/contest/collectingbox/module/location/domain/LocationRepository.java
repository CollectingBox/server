package contest.collectingbox.module.location.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "select dong from location " +
            "where match (dong) against (:keyword in natural language mode) limit 1", nativeQuery = true)
    String findDongByKeyword(@Param("keyword") String keyword);
}
