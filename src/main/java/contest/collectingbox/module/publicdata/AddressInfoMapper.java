package contest.collectingbox.module.publicdata;

import contest.collectingbox.module.collectingbox.domain.Tag;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressInfoMapper {
    public static AddressInfoDto jsonObjectToAddressInfoDto(JSONObject document, Tag tag) {
        JSONObject address = document.getJSONObject("address");
        JSONObject roadAddress = document.getJSONObject("road_address");
        return AddressInfoDto.builder()
                .longitude(document.getDouble("x"))
                .latitude(document.getDouble("y"))
                .sido(address.getString("region_1depth_name"))
                .sigungu(address.getString("region_2depth_name"))
                .dong(address.getString("region_3depth_h_name"))
                .roadName(roadAddress.getString("address_name"))
                .streetNum(address.getString("address_name"))
                .name(getDetailName(tag, roadAddress.getString("building_name")))
                .tag(tag)
                .build();
    }

    private static String getDetailName(Tag tag, String buildingName) {
        return buildingName.isEmpty() ?
                (tag.name().equals("TRASH") ? tag.getLabel() : tag.getLabel() + " 수거함")
                : buildingName + " 근처 수거함";
    }
}
