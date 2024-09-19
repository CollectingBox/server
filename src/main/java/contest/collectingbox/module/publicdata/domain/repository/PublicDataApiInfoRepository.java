package contest.collectingbox.module.publicdata.domain.repository;

import contest.collectingbox.module.publicdata.domain.PublicDataApiInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicDataApiInfoRepository extends JpaRepository<PublicDataApiInfo, Long> {
}
