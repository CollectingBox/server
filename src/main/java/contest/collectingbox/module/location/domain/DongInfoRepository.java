package contest.collectingbox.module.location.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DongInfoRepository extends JpaRepository<DongInfo, Long> {
    DongInfo findBySigunguNmAndDongNm(String sigunguNm, String dongNm);
}
