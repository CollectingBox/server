package contest.collectingbox.module.review.presentation;

import contest.collectingbox.global.common.ApiResponse;
import contest.collectingbox.module.review.application.ReviewService;
import contest.collectingbox.module.review.dto.ReviewRequest;
import io.micrometer.common.util.StringUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.util.StringUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "review", description = "수거함 리뷰 API")
@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "수거함 리뷰 등록", description = "수거함 ID에 해당되는 수거함에 리뷰를 등록합니다.")
    @PostMapping("/{collectionId}/review")
    public ApiResponse<Long> postReview(@RequestBody @Valid ReviewRequest dto, @PathVariable Long collectionId) {
        return ApiResponse.ok(reviewService.postReview(dto, collectionId));
    }
}
