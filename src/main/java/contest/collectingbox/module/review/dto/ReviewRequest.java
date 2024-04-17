package contest.collectingbox.module.review.dto;

import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.review.domain.Review;
import contest.collectingbox.module.review.domain.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ReviewRequest {
    @Schema(description = "리뷰 내용", example = "EXIST")
    private String content;

    public Review toEntity(CollectingBox collectingBox) {
        return Review.builder().
                collectingBox(collectingBox)
                .tag(Tag.valueOf(content))
                .build();
    }
}
