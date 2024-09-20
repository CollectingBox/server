package contest.collectingbox.module.publicdata.domain;

import contest.collectingbox.global.exception.CollectingBoxException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static contest.collectingbox.global.exception.ErrorCode.UNEXPECTED_ERROR_EXTERNAL_API;
import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class OpenDataApiManager {

    private static final String API_URL = "https://api.odcloud.kr/api%s";

    @Value("${public-data.api.key}")
    private String apiKey;

    public int fetchTotalCount(String callAddress) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl(callAddress, 1), String.class);
        if (isError(response)) {
            throw new CollectingBoxException(UNEXPECTED_ERROR_EXTERNAL_API);
        }
        return new JSONObject(response.getBody()).getInt("totalCount");
    }

    public JSONArray fetchOpenData(String callAddress, int perPage) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl(callAddress, perPage), String.class);
        if (isError(response)) {
            throw new CollectingBoxException(UNEXPECTED_ERROR_EXTERNAL_API);
        }
        return new JSONObject(response.getBody()).getJSONArray("data");
    }

    private URI getUrl(String callAddress, int perPage) {
        return UriComponentsBuilder.fromUriString(String.format(API_URL, callAddress))
                .queryParam("serviceKey", apiKey)
                .queryParam("perPage", perPage)
                .build().encode(UTF_8).toUri();
    }

    private boolean isError(ResponseEntity<String> response) {
        return response.getStatusCode().isError();
    }
}
