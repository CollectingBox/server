package contest.collectingbox.module.publicdata.presentation;

import contest.collectingbox.global.common.ApiResponse;
import contest.collectingbox.module.publicdata.application.PublicDataService;
import contest.collectingbox.module.publicdata.dto.LoadCsvPublicDataRequest;
import contest.collectingbox.module.publicdata.dto.LoadPublicDataRequest;
import contest.collectingbox.module.publicdata.dto.SavePublicDataApiInfoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PublicDataApiController {

    private final PublicDataService publicDataService;

    @PostMapping("/public-data/info")
    public ApiResponse<Integer> savePublicDataApiInfo(@RequestBody List<SavePublicDataApiInfoRequest> requests) {
        publicDataService.savePublicDataApiInfo(requests);
        return ApiResponse.ok(requests.size());
    }

    @PostMapping("/public-data/load")
    public ApiResponse<Long> loadPublicData(@RequestBody List<LoadPublicDataRequest> requests) {
        return ApiResponse.ok(publicDataService.loadPublicData(requests));
    }

    @PostMapping("/public-data/csv")
    public ApiResponse<Long> loadCsvPublicData(@RequestBody List<LoadCsvPublicDataRequest> requests) {
        long loadedDataCount = 0;
        for (LoadCsvPublicDataRequest request : requests) {
            loadedDataCount += publicDataService.loadCsvPublicData(request);
        }
        return ApiResponse.ok(loadedDataCount);
    }
}
