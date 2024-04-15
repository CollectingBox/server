package contest.collectingbox.module.location.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

    private String sido;
    private String sigungu;
    private String dong;
    private String rodeName;
    private String streetNum;


    public Address(String sigungu, String dong) {
        this.sigungu = sigungu;
        this.dong = dong;
    }
}
