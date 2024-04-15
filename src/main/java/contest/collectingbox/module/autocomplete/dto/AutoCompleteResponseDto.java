package contest.collectingbox.module.autocomplete.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class AutoCompleteResponseDto {
    private List<String> items = new ArrayList<>();

    public AutoCompleteResponseDto(List<String> items) {
        this.items = items;
    }

}
