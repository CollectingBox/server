package contest.collectingbox.module.publicdata.domain;

import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.publicdata.dto.AddressInfoDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import static contest.collectingbox.module.publicdata.dto.AddressInfoDto.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressInfoMapper {
    public static AddressInfoDto jsonObjectToAddressInfoDto(JSONObject document, Tag tag) {
        JSONObject address = document.getJSONObject("address");
        JSONObject roadAddress = document.getJSONObject("road_address");

        AddressInfoDtoBuilder builder = builder()
                .longitude(document.getDouble("x"))
                .latitude(document.getDouble("y"))
                .tag(tag);

        return buildWithRoadAddress(buildWithAddress(builder, address), roadAddress, tag).build();
    }

    private static AddressInfoDtoBuilder buildWithAddress(AddressInfoDtoBuilder builder, JSONObject address) {
        if (address.isEmpty()) {
            return builder;
        }
        return builder
                .sido(address.getString("region_1depth_name"))
                .sigungu(address.getString("region_2depth_name"))
                .dong(address.getString("region_3depth_h_name"))
                .streetNum(address.getString("address_name"));
    }

    private static AddressInfoDtoBuilder buildWithRoadAddress(AddressInfoDtoBuilder builder, JSONObject roadAddress, Tag tag) {
        if (roadAddress.isEmpty()) {
            return builder.name(tag.name().equals("TRASH") ? tag.getLabel() : tag.getLabel() + " 수거함");
        }
        return builder
                .roadName(roadAddress.getString("address_name"))
                .name(roadAddress.getString("building_name") + " 근처 수거함");
    }
}
