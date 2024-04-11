package contest.collectingbox.module.location.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String sido;
    private String sigungu;
    private String dong;
    private String rodeName;
    private String streetNum;
}
