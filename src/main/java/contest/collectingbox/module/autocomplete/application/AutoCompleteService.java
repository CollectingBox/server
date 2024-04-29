package contest.collectingbox.module.autocomplete.application;

import contest.collectingbox.module.autocomplete.domain.AutoCompleteRepository;
import contest.collectingbox.module.autocomplete.dto.AddressDto;
import contest.collectingbox.module.autocomplete.dto.AutoCompleteResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AutoCompleteService {
    private final AutoCompleteRepository autoCompleteRepository;

    public AutoCompleteResponseDto getAutoComplete(String query) {
        List<String> items = query.isEmpty() ? new ArrayList<>() :
                autoCompleteRepository.findAutoComplete(query)
                        .stream()
                        .map(this::formatAddress)
                        .distinct()
                        .collect(Collectors.toList());
        return new AutoCompleteResponseDto(items);
    }

    private String formatAddress(AddressDto address) {
        return address.getSigungu() + " " + address.getDong();
    }
}
