package contest.collectingbox.module.review.dto;

import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.review.domain.Review;
import contest.collectingbox.module.review.domain.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class ReviewRequest {
    private String content;

    public Review toEntity(CollectingBox collectingBox) {
        return Review.builder().
                collectingBox(collectingBox)
                .tag(Tag.valueOf(content))
                .build();
    }
}
