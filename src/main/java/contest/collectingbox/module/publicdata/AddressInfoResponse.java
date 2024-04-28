package contest.collectingbox.module.publicdata;

import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.location.domain.Address;
import contest.collectingbox.module.location.domain.Location;
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

    public CollectingBox toEntity() {
        Location location = Location.builder()
                .name(name)
                .point(GeometryUtil.toPoint(longitude, latitude))
                .address(getAddress(sido, sigungu, dong, roadName, streetNum))
                .build();

        return CollectingBox.builder()
                .location(location)
                .tag(tag)
                .build();
    }

    private Address getAddress(String sido, String sigungu, String dong, String roadName, String streetNum) {
        return Address.builder()
                .sido(sido)
                .sigungu(sigungu)
                .dong(dong)
                .roadName(roadName)
                .streetNum(streetNum)
                .build();
    }
}
