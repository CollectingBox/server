package contest.collectingbox.module.location.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String sido;
    private String sigungu;
    private String dong;
    private String rodeName;
    private String streetNum;
}
