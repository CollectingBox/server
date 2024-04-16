package contest.collectingbox.module.review.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewResponse {
    private String status;
    private String createdDate;

    @QueryProjection
    public ReviewResponse(String status, String createdDate) {
        this.status = status;
        this.createdDate = formatDate(createdDate);
    }

    private String formatDate(String inputDate) {
        LocalDateTime dateTime = LocalDateTime.parse(inputDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        return dateTime.format(DateTimeFormatter.ofPattern("yy.MM.dd"));
    }
}
