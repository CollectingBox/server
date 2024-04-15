package contest.collectingbox.module.collectingbox.domain;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectingBoxRepository extends JpaRepository<CollectingBox, Long>, CollectingBoxRepositoryCustom {

    @Query("select c from CollectingBox c join c.location l " +
            "where function('st_contains', function('st_buffer', :center, :radius), l.point) " +
            "and c.tag in :tags")
    List<CollectingBox> findAllWithinArea(@Param("center") Point center,
                                          @Param("radius") int radius,
                                          @Param("tags") List<Tag> tags);
}
