package contest.collectingbox.module.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        this.createdDate = formatDate(createdDate);
    }

    private String formatDate(String inputDate) {
        LocalDateTime dateTime = LocalDateTime.parse(inputDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        return dateTime.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
    }
}
