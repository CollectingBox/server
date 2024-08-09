package contest.collectingbox.module.location.domain.repository;

import contest.collectingbox.module.location.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
