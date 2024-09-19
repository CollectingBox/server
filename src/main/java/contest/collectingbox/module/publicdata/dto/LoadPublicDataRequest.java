package contest.collectingbox.module.publicdata.dto;

import contest.collectingbox.module.collectingbox.domain.Tag;
import lombok.Data;

@Data
public class LoadPublicDataRequest {

    private String sigungu;
    private Tag tag;
    private String callAddress;

    public String getUrlWithPerPage(String apiKey, int perPage) {
        return String.format("https://api.odcloud.kr/api%s?serviceKey=%s&perPage=%d", callAddress, apiKey, perPage);
    }
}
