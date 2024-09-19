package contest.collectingbox.module.publicdata.domain;

import contest.collectingbox.global.exception.CollectingBoxException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static contest.collectingbox.global.exception.ErrorCode.UNEXPECTED_ERROR_EXTERNAL_API;

@Component
public class OpenDataApiManager {

    private static final String API_URL = "https://api.odcloud.kr/api%s?serviceKey=%s&perPage=%d";

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

    private String getUrl(String callAddress, int perPage) {
        return String.format(API_URL, callAddress, apiKey, perPage);
    }

    private boolean isError(ResponseEntity<String> response) {
        return response.getStatusCode().isError();
    }
}
