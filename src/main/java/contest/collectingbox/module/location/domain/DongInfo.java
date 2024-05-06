package contest.collectingbox.module.location.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DongInfo {

    @Id
    private Long dongCd;
    private String sidoNm;
    private String sigunguNm;
    private String dongNm;
}
