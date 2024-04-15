package contest.collectingbox.module.autocomplete.presentation;

import contest.collectingbox.global.common.ApiResponse;
import contest.collectingbox.module.autocomplete.application.AutoCompleteService;
import contest.collectingbox.module.autocomplete.dto.AutoCompleteResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/collections/search/autocomplete")
public class AutoCompleteController {
    private final AutoCompleteService autoCompleteService;

    @GetMapping
    public ApiResponse<AutoCompleteResponseDto> getAutoComplete(@RequestParam String query) {
        log.info("query = {}", query);
        AutoCompleteResponseDto response = autoCompleteService.getAutoComplete(query);
        return ApiResponse.ok(response);
    }
}
