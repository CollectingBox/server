package contest.collectingbox.module.collectingbox.presentation;

import contest.collectingbox.global.common.ApiResponse;
import contest.collectingbox.module.collectingbox.application.CollectingBoxService;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxDetailResponse;
import contest.collectingbox.module.collectingbox.dto.CollectingBoxResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@io.swagger.v3.oas.annotations.tags.Tag(name = "collecting-box", description = "수거함 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/collections")
public class CollectingBoxController {

    private final CollectingBoxService collectingBoxService;

    @Operation(summary = "수거함 목록 조회", description = "위도와 경도를 기준으로 200m 반경에 위치한 수거함 목록을 조회합니다.")
    @ApiResponses(value = {
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "BAD_REQUEST")
    })
    @GetMapping
    public ApiResponse<List<CollectingBoxResponse>> findCollectingBoxesWithinArea(@RequestParam Double latitude,
                                                                                  @RequestParam Double longitude,
                                                                                  @RequestParam List<Tag> tags) {
        return ApiResponse.ok(collectingBoxService.findCollectingBoxesWithinArea(latitude, longitude, tags));
    }

    @Operation(summary = "지역별 수거함 검색", description = "구/동 단위로 검색한 주소에 위치한 수거함 목록을 조회합니다.")
    @GetMapping("/search")
    public ApiResponse<List<CollectingBoxResponse>> searchCollectingBoxes(@RequestParam String query,
                                                                          @RequestParam List<Tag> tags) {
        return ApiResponse.ok(collectingBoxService.searchCollectingBoxes(query, tags));
    }

    @Operation(summary = "수거함 상세정보 조회", description = "수거함 ID별 수거함 상세정보를 조회합니다.")
    @GetMapping("/{collectionId}")
    public ApiResponse<CollectingBoxDetailResponse> findBoxDetailById(@PathVariable Long collectionId) {
        CollectingBoxDetailResponse response = collectingBoxService.findBoxDetailById(collectionId);
        return ApiResponse.ok(response);
    }

}
