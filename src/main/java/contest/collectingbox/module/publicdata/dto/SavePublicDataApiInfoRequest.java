package contest.collectingbox.module.publicdata.dto;

import contest.collectingbox.module.collectingbox.domain.Tag;
import contest.collectingbox.module.publicdata.domain.PublicDataApiInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SavePublicDataApiInfoRequest {
    private String sido;
    private String sigungu;
    private Tag tag;
    private String callAddress;

    public PublicDataApiInfo toEntity() {
        return PublicDataApiInfo.builder()
                .sido(sido)
                .sigungu(sigungu)
                .tag(tag)
                .callAddress(callAddress)
                .build();
    }
}
