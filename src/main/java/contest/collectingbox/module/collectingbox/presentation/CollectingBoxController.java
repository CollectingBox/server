package contest.collectingbox.module.collectingbox.presentation;

import contest.collectingbox.global.common.ApiResponse;
import contest.collectingbox.module.collectingbox.application.CollectingBoxService;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collections")
public class CollectingBoxController {

    private final CollectingBoxService collectingBoxService;

    @GetMapping
    public ApiResponse<List<CollectingBoxResponse>> findCollectingBoxesWithinArea(@RequestParam Double latitude,
                                                                                  @RequestParam Double longitude,
                                                                                  @RequestParam List<Tag> tags) {
        return ApiResponse.ok(collectingBoxService.findCollectingBoxesWithinArea(latitude, longitude, tags));
    }

    @GetMapping("/search")
    public ApiResponse<List<CollectingBoxResponse>> searchCollectingBoxes(@RequestParam String query,
                                                                          @RequestParam List<Tag> tags) {
        return ApiResponse.ok(collectingBoxService.searchCollectingBoxes(query, tags));
    }

    @GetMapping("/{collectionId}")
    public ApiResponse<CollectingBoxDetailResponse> findBoxDetailById(@PathVariable Long collectionId) {
        CollectingBoxDetailResponse response = collectingBoxService.findBoxDetailById(collectionId);
        return ApiResponse.ok(response);
    }

}
