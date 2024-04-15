package contest.collectingbox.module.autocomplete.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AddressDto {
    private String sigungu;
    private String dong;

    @QueryProjection
    public AddressDto(String sigungu, String dong) {
        this.sigungu = sigungu;
        this.dong = dong;
    }
}
