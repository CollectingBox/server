package contest.collectingbox.module.collectingbox.dto;

import contest.collectingbox.module.collectingbox.domain.CollectingBox;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CollectingBoxResponse {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String tag;

    public static CollectingBoxResponse fromEntity(CollectingBox collectingBox) {
        return CollectingBoxResponse.builder()
                .id(collectingBox.getId())
                .latitude(collectingBox.getLocation().latitude())
                .longitude(collectingBox.getLocation().longitude())
                .tag(collectingBox.getTag().getLabel())
                .build();
    }
}
