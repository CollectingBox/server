package contest.collectingbox.module.publicdata;

import static java.nio.charset.StandardCharsets.*;

import contest.collectingbox.global.exception.CollectingBoxException;
import contest.collectingbox.module.collectingbox.domain.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoApiManager {
    @Value("${kakao.api.key}")
    private String apiKey;
    private final String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";

    public AddressInfoResponse fetchAddressInfo(String query, Tag tag) {
        try {

            ResponseEntity<String> response = callKakaoAPI(query);
            if (response.getStatusCode() != HttpStatus.OK) {
                log.error("Kakao API call failed status : {}", response.getStatusCode());
                throw new RuntimeException("Kakao API fail exception");
            }

            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray documents = jsonObject.getJSONArray("documents");

            if (documents.isEmpty()) { // API 호출 후 응답값이 없는 경우 추후 처리 필요
                log.warn("No address information found for query: {}", query);
                return null;
            }

            JSONObject document = documents.getJSONObject(0);
            return makeAddressDto(document, tag);
        } catch (JSONException e) {
            log.error("Error parsing JSON from Kakao API : {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private ResponseEntity<String> callKakaoAPI(String query) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "KakaoAK " + apiKey);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = UriComponentsBuilder.fromUriString(apiUrl).queryParam("query", query)
                .build().encode(UTF_8).toUri();

        return restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity,
                String.class);
    }

    private AddressInfoResponse makeAddressDto(JSONObject document, Tag tag) {
        JSONObject address = document.getJSONObject("address");
        JSONObject roadAddress = document.getJSONObject("road_address");

        String buildingName = roadAddress.getString("building_name");
        String detailName = buildingName.isEmpty() ?
                (tag.name().equals("TRASH") ? tag.getLabel() : tag.getLabel() + " 수거함")
                : buildingName;

        return AddressInfoResponse.builder()
                .longitude(document.getString("x"))
                .latitude(document.getString("y"))
                .sido(address.getString("region_1depth_name"))
                .sigungu(address.getString("region_2depth_name"))
                .dong(address.getString("region_3depth_name"))
                .road_name(roadAddress.getString("address_name"))
                .street_num(address.getString("address_name"))
                .name(detailName)
                .tag(tag)
                .build();

    }
}
