package contest.collectingbox.module.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import contest.collectingbox.global.common.CommonUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponse {
    @Schema(description = "리뷰 내용", example = "EXIST")
    private String content;

    @Schema(description = "작성 날짜", example = "24.04.17")
    private String createdDate;

    @QueryProjection
    public ReviewResponse(String content, String createdDate) {
        this.content = content;
        this.createdDate = CommonUtils.formatDate(createdDate);
    }

}
