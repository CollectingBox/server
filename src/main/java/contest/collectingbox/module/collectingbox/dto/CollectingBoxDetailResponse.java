package contest.collectingbox.module.collectingbox.dto;

import com.querydsl.core.annotations.QueryProjection;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.review.dto.ReviewResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CollectingBoxDetailResponse {
    private String location;
    private String roadName;
    private String streetNumber;
    private String modifiedDate;
    private String tag;
    @Setter
    private List<ReviewResponse> reviews;

    @QueryProjection
    @Builder
    public CollectingBoxDetailResponse(String location, String roadName, String streetNumber, String modifiedDate,
                                       String tag) {
        this.location = location;
        this.roadName = roadName;
        this.streetNumber = streetNumber;
        this.modifiedDate = formatDate(modifiedDate);
        this.tag = Tag.of(tag);
    }

    private String formatDate(String inputDate) {
        LocalDateTime dateTime = LocalDateTime.parse(inputDate,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS"));
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

}
