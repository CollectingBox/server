package contest.collectingbox.module.review.presentation;

import contest.collectingbox.global.common.ApiResponse;
import contest.collectingbox.module.review.application.ReviewService;
import contest.collectingbox.module.review.dto.ReviewRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/{collectionId}/review")
    public ApiResponse<Long> postReview(@RequestBody ReviewRequest dto, @PathVariable Long collectionId) {
        log.info("collectionID for post review = {}", collectionId);
        return ApiResponse.ok(reviewService.postReview(dto, collectionId));
    }
}
