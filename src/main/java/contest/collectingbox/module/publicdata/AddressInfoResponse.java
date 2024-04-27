package contest.collectingbox.module.publicdata;

import contest.collectingbox.module.collectingbox.domain.Tag;
import lombok.*;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoResponse {
    private Double longitude;
    private Double latitude;
    private String sido;
    private String sigungu;
    private String dong;
    private String roadName;
    private String streetNum;
    private String name;
    private Tag tag;

    public boolean hasNull() {
        return longitude == null ||
                latitude == null ||
                sido == null ||
                sigungu == null ||
                dong == null ||
                (name == null && roadName == null && streetNum == null) ||
                tag == null;
    }
}
