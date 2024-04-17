package contest.collectingbox.module.collectingbox.domain;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectingBoxRepository extends JpaRepository<CollectingBox, Long>, CollectingBoxRepositoryCustom {

    @Query("select c from CollectingBox c join c.location l " +
            "where function('st_contains', function('st_buffer', :center, :radius), l.point) and " +
            "c.tag in :tags")
    List<CollectingBox> findAllWithinArea(@Param("center") Point center,
                                          @Param("radius") int radius,
                                          @Param("tags") List<Tag> tags);


    @Query(value = "select c.* from collecting_box as c " +
            "join location as l on c.location_id = l.id " +
            "where match (l.dong) against (:dong in natural language mode) and " +
            "c.tag in (:tags)", nativeQuery = true)
    List<CollectingBox> findAllByDong(@Param("dong") String dong,
                                      @Param("tags") List<String> tags);

    @Query(value = "select c.* from collecting_box as c " +
            "join location as l on c.location_id = l.id " +
            "where match(l.sigungu) against (:keyword in natural language mode) and " +
            "c.tag in (:tags)", nativeQuery = true)
    List<CollectingBox> findAllByKeyword(@Param("keyword") String keyword,
                                         @Param("tags") List<String> tags);
}
