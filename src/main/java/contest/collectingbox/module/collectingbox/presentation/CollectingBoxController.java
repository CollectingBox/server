package contest.collectingbox.module.collectingbox.presentation;

import contest.collectingbox.global.common.ApiResponse;
import contest.collectingbox.module.collectingbox.application.CollectingBoxService;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@io.swagger.v3.oas.annotations.tags.Tag(name = "collecting-box", description = "수거함 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/collections")
public class CollectingBoxController {

    private final CollectingBoxService collectingBoxService;

    @Operation(summary = "주변 수거함 목록 조회", description = "위도와 경도를 기준으로 주변 600m 반경에 위치한 수거함 목록을 조회합니다.")
    @GetMapping
    public ApiResponse<List<CollectingBoxResponse>> findCollectingBoxesWithinArea(@RequestParam final double longitude,
                                                                                  @RequestParam final double latitude,
                                                                                  @RequestParam final List<Tag> tags) {
        return ApiResponse.ok(collectingBoxService.findCollectingBoxesWithinArea(longitude, latitude, tags));
    }

    @Operation(summary = "지역별 수거함 검색", description = "구/동 단위로 검색한 주소에 위치한 수거함 목록을 조회합니다.")
    @GetMapping("/search")
    public ApiResponse<List<CollectingBoxResponse>> searchCollectingBoxes(@RequestParam final String query,
                                                                          @RequestParam final List<Tag> tags) {
        return ApiResponse.ok(collectingBoxService.searchCollectingBoxes(query, tags));
    }

    @Operation(summary = "수거함 상세정보 조회", description = "수거함 ID별 수거함 상세정보를 조회합니다.")
    @GetMapping("/{collectionId}")
    public ApiResponse<CollectingBoxDetailResponse> findBoxDetailById(@PathVariable Long collectionId) {
        CollectingBoxDetailResponse response = collectingBoxService.findBoxDetailById(collectionId);
        return ApiResponse.ok(response);
    }
}
