package contest.collectingbox.module.review.dto;

import static contest.collectingbox.global.exception.ErrorCode.*;

import contest.collectingbox.global.exception.CollectingBoxException;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.review.domain.Review;
import contest.collectingbox.module.review.domain.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@ToString
public class ReviewRequest {
    @Schema(description = "리뷰 내용", example = "EXIST", allowableValues = {"EXIST", "DISAPPEAR"})
    @NotNull(message = "리뷰 내용을 입력하세요.")
    private String content;

    public Review toEntity(CollectingBox collectingBox) {
        isValidContent(content);
        return Review.builder().
                collectingBox(collectingBox)
                .tag(Tag.valueOf(content))
                .build();
    }

    private void isValidContent(String content) {
        try {
            Tag.valueOf(content);
        } catch (IllegalArgumentException e) {
            throw new CollectingBoxException(INVALID_REVIEW_CONTENT);
        }
    }
}
