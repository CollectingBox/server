package contest.collectingbox.module.publicdata;

import contest.collectingbox.global.utils.GeometryUtil;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.location.domain.DongInfoRepository;
import contest.collectingbox.module.location.domain.Location;
import lombok.*;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfoDto {
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

    public boolean hasEmptyValue() {
        return sido.isEmpty() || sigungu.isEmpty() || dong.isEmpty() ||
                (name.isEmpty() && roadName.isEmpty() && streetNum.isEmpty())
                || tag.name().isEmpty();
    }

    public CollectingBox toCollectingBox(DongInfoRepository repository) {
        Location location = Location.builder()
                .dongInfo(repository.findBySigunguNmAndDongNm(sigungu, dong))
                .name(name)
                .roadName(roadName)
                .streetNum(streetNum)
                .point(GeometryUtil.toPoint(longitude, latitude))
                .build();

        return CollectingBox.builder()
                .location(location)
                .tag(tag)
                .build();
    }
}
