package contest.collectingbox.module.autocomplete.domain;

import contest.collectingbox.module.autocomplete.dto.AddressDto;
import java.util.List;

public interface AutoCompleteRepositoryCustom {
    List<AddressDto> findAutoComplete(String query);
}
