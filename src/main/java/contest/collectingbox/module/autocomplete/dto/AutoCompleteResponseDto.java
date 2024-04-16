package contest.collectingbox.module.autocomplete.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Getter
@NoArgsConstructor
public class AutoCompleteResponseDto {

    @Schema(description = "검색어 자동완성 목록", example = "[\"강남구 대치동\", \"강남구 삼성동\", \"강남구 청담동\"]")
    private List<String> items = new ArrayList<>();

    public AutoCompleteResponseDto(List<String> items) {
        this.items = items;
    }

}
