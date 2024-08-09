package contest.collectingbox.module.location.domain.repository;

import contest.collectingbox.module.location.domain.DongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DongInfoRepository extends JpaRepository<DongInfo, Long> {
    DongInfo findBySigunguNmAndDongNm(String sigunguNm, String dongNm);

    @Query("select d from DongInfo d where d.dongNm = :dongNm")
    DongInfo findByDongNm(@Param("dongNm") String dongNm);
}
