package contest.collectingbox.module.publicdata.domain;

import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.publicdata.dto.AddressInfoDto;
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

    public AddressInfoDto fetchAddressInfo(String query, Tag tag) {
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
            if (document.isNull("address") || document.isNull("road_address")) {
                return null;
            }

            return AddressInfoMapper.jsonObjectToAddressInfoDto(document, tag);
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
}
