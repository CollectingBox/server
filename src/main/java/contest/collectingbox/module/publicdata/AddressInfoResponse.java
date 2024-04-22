package contest.collectingbox.module.publicdata;

import contest.collectingbox.module.collectingbox.domain.Tag;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class AddressInfoResponse {
    private String longitude;
    private String latitude;
    private String sido;
    private String sigungu;
    private String dong;
    private String roadName;
    private String streetNum;
    private String name;
    private Tag tag;

    @Builder
    public AddressInfoResponse(String longitude, String latitude, String sido, String sigungu, String dong,
                               String roadName, String streetNum, String name, Tag tag) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.sido = sido;
        this.sigungu = sigungu;
        this.dong = dong;
        this.roadName = roadName;
        this.streetNum = streetNum;
        this.name = name;
        this.tag = tag;
    }

}
