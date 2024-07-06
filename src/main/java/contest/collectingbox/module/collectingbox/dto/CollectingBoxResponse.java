package contest.collectingbox.module.collectingbox.dto;

import com.querydsl.core.annotations.QueryProjection;
import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import contest.collectingbox.module.collectingbox.domain.Tag;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectingBoxResponse {

    @Schema(description = "수거함 ID", example = "2571")
    private Long id;

    @Schema(description = "경도", example = " 126.902998281977")
    private Double longitude;

    @Schema(description = "위도", example = "37.4888178446615")
    private Double latitude;

    @Schema(description = "태그", example = "CLOTHES",
            allowableValues = {"CLOTHES", "LAMP_BATTERY", "MEDICINE", "TRASH"})
    private Tag tag;

    @QueryProjection
    public CollectingBoxResponse(Long id, double longitude, double latitude, Tag tag) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.tag = tag;
    }

    public static CollectingBoxResponse fromEntity(CollectingBox collectingBox) {
        return CollectingBoxResponse.builder()
                .id(collectingBox.getId())
                .latitude(collectingBox.getLocation().latitude())
                .longitude(collectingBox.getLocation().longitude())
                .tag(collectingBox.getTag())
                .build();
    }
}
