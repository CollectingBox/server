package contest.collectingbox.module.collectingbox.domain.repository;

import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.location.domain.DongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectingBoxRepository extends JpaRepository<CollectingBox, Long>, CollectingBoxRepositoryCustom {

    @Query("select c from CollectingBox c join c.location l where l.dongInfo = :dongInfo and c.tag in :tags")
    List<CollectingBox> findAllByDongInfoAndTags(@Param("dongInfo") DongInfo dongInfo,
                                                 @Param("tags") List<Tag> tags);
}
