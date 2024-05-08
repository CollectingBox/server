package contest.collectingbox.module.location.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DongInfoRepository extends JpaRepository<DongInfo, Long> {
    DongInfo findBySigunguNmAndDongNm(String sigunguNm, String dongNm);

    @Query("select d from DongInfo d where d.dongNm = :dongNm")
    DongInfo findByDongNm(@Param("dongNm") String dongNm);

    @Query("select d from DongInfo d where d.sigunguNm = :sigunguNm")
    List<DongInfo> findAllBySigunguNm(@Param("sigunguNm") String sigungNm);
}
