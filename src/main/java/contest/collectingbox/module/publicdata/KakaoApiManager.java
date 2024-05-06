package contest.collectingbox.module.publicdata;

import contest.collectingbox.module.collectingbox.domain.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoApiManager {
    private static final String API_URL = "https://dapi.kakao.com/v2/local/search/address.json";

    @Value("${kakao.api.key}")
    private String apiKey;

    public AddressInfoResponse fetchAddressInfo(String query, Tag tag) {
        try {
            ResponseEntity<String> response = callKakaoAPI(query);

            // 카카오 API 예외 처리
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Kakao API failed status = " + response.getStatusCode());
            }

            // 주소 정보 추출
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray documents = jsonObject.getJSONArray("documents");

            if (documents.isEmpty()) { // API 호출 후 응답값이 없는 경우 추후 처리 필요
                log.warn("No address information found for query: {}", query);
                return null;
            }

            JSONObject document = documents.getJSONObject(0);
            return makeAddressDto(document, tag);
        } catch (JSONException e) {
            throw new RuntimeException("Error parsing JSON from Kakao API", e);
        }
    }

    private ResponseEntity<String> callKakaoAPI(String query) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + apiKey);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder.fromUriString(API_URL).queryParam("query", query)
                .build().encode(UTF_8).toUri();

        return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, String.class);
    }

    private AddressInfoResponse makeAddressDto(JSONObject document, Tag tag) {
        AddressInfoResponse.AddressInfoResponseBuilder builder = AddressInfoResponse.builder()
                .longitude(document.getDouble("x"))
                .latitude(document.getDouble("y"))
                .tag(tag);

        if (document.isNull("address")) {
            return getResponseWithRoadAddress(document, tag, builder);
        }

        if (document.isNull("road_address")) {
            return getResponseWithAddress(document, tag, builder);
        }

        return getResponse(document, tag, builder);
    }

    private AddressInfoResponse getResponseWithRoadAddress(JSONObject document,
                                                           Tag tag,
                                                           AddressInfoResponse.AddressInfoResponseBuilder builder) {
        JSONObject roadAddress = document.getJSONObject("road_address");
        return builder.name(getDetailName(tag, roadAddress.getString("building_name")))
                .sido(roadAddress.getString("region_1depth_name"))
                .sigungu(roadAddress.getString("region_2depth_name"))
                .dong(roadAddress.getString("region_3depth_name"))
                .roadName(roadAddress.getString("address_name"))
                .build();
    }

    private AddressInfoResponse getResponseWithAddress(JSONObject document, Tag tag,
                                                       AddressInfoResponse.AddressInfoResponseBuilder builder) {
        JSONObject address = document.getJSONObject("address");
        return builder.name(getDetailName(tag, ""))
                .sido(address.getString("region_1depth_name"))
                .sigungu(address.getString("region_2depth_name"))
                .dong(address.getString("region_3depth_h_name"))
                .streetNum(address.getString("address_name"))
                .build();
    }

    private AddressInfoResponse getResponse(JSONObject document,
                                            Tag tag,
                                            AddressInfoResponse.AddressInfoResponseBuilder builder) {
        JSONObject address = document.getJSONObject("address");
        JSONObject roadAddress = document.getJSONObject("road_address");
        return builder
                .sido(address.getString("region_1depth_name"))
                .sigungu(address.getString("region_2depth_name"))
                .dong(address.getString("region_3depth_h_name"))
                .name(getDetailName(tag, roadAddress.getString("building_name")))
                .streetNum(address.getString("address_name"))
                .roadName(roadAddress.getString("address_name"))
                .build();
    }

    private String getDetailName(Tag tag, String buildingName) {
        return buildingName.isEmpty() ?
                (tag.name().equals("TRASH") ? tag.getLabel() : tag.getLabel() + " 수거함")
                : buildingName + " 근처 수거함";
    }
}
