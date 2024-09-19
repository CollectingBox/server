package contest.collectingbox.module.collectingbox.domain.repository;

import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectingBoxRepository extends JpaRepository<CollectingBox, Long>, CollectingBoxRepositoryCustom {
}
