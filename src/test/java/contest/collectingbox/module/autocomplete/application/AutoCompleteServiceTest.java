package contest.collectingbox.module.autocomplete.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import contest.collectingbox.module.autocomplete.domain.AutoCompleteRepository;
import contest.collectingbox.module.autocomplete.dto.AddressDto;
import contest.collectingbox.module.autocomplete.dto.AutoCompleteResponseDto;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class AutoCompleteServiceTest {
    @InjectMocks
    private AutoCompleteService autoCompleteService;
    @Mock
    private AutoCompleteRepository autoCompleteRepository;


    @DisplayName("검색한 키워드가 포함된 주소 자동완성 목록을 조회한다.")
    @Test
    void getAutoCompletes_Success_WithKeyword() {
        // given
        String keyword = "상";
        // mock
        given(autoCompleteRepository.findAutoComplete(keyword)).willReturn(
                List.of(new AddressDto("강동", "상일"), new AddressDto("노원", "상계")));

        // when
        AutoCompleteResponseDto response = autoCompleteService.getAutoComplete(keyword);

        // then
        assertThat(response.getItems().size()).isEqualTo(2);
        assertThat(response.getItems()).containsExactly("강동구 상일동", "노원구 상계동");
    }

}