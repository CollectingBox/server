package contest.collectingbox.module.publicdata;

import contest.collectingbox.global.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PublicDataApiController {

    private static final String TOTAL_COUNT_KEY = "totalCount";

    private final PublicDataService publicDataService;

    @Value("${public-data.api.key}")
    private String apiKey;

    @PostMapping("/public-data/info")
    public ApiResponse<Integer> savePublicDataApiInfo(@RequestBody List<SavePublicDataApiInfoRequest> requests) {
        publicDataService.savePublicDataApiInfo(requests);
        return ApiResponse.ok(requests.size());
    }

    @PostMapping("/public-data/load")
    public ApiResponse<Long> loadPublicData(@RequestBody List<LoadPublicDataRequest> requests) {
        long loadedDataCount = 0;
        for (LoadPublicDataRequest request : requests) {
            try {
                System.out.printf("======= %s - %s =======%n", request.getSigungu(), request.getTag().getLabel());
                int totalCount = getTotalCountOfPublicData(request);
                loadedDataCount += publicDataService.loadPublicData(callPublicDataApi(request, totalCount), request.getTag());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return ApiResponse.ok(loadedDataCount);
    }

    private JSONObject callPublicDataApi(LoadPublicDataRequest request, int perPage) throws IOException {
        URL url = new URL(request.getUrlWithPerPage(apiKey, perPage));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(HttpMethod.GET.name());
        urlConnection.setRequestProperty("Content-type", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
        return new JSONObject(br.readLine());
    }

    private int getTotalCountOfPublicData(LoadPublicDataRequest request) throws IOException {
        return Integer.parseInt(callPublicDataApi(request, 1).get(TOTAL_COUNT_KEY).toString());
    }
}
