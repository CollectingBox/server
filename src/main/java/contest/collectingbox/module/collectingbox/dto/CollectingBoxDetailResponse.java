package contest.collectingbox.module.collectingbox.dto;

import com.querydsl.core.annotations.QueryProjection;
import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.review.dto.ReviewResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.management.Descriptor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class CollectingBoxDetailResponse {
    @Schema(description = "상세위치", example = "이마트24")
    private String location;
    @Schema(description = "도로명 주소", example = "서울특별시 강남구 도산대로66길 43")
    private String roadName;
    @Schema(description = "지번", example = "서울특별시 강남구 청담동 16-19")
    private String streetNumber;
    @Schema(description = "최종 수정일", example = "2024.04.17")
    private String modifiedDate;
    @Schema(description = "수거함 분류", example = "폐의류")
    private String tag;
    @Schema(description = "리뷰 목록")
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
