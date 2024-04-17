package contest.collectingbox.module.autocomplete.presentation;

import contest.collectingbox.global.common.ApiResponse;
import contest.collectingbox.module.autocomplete.application.AutoCompleteService;
import contest.collectingbox.module.autocomplete.dto.AutoCompleteResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "search-auto-complete", description = "검색어 자동 완성 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/collections/search/autocomplete")
public class AutoCompleteController {
    private final AutoCompleteService autoCompleteService;

    @Operation(summary = "검색어 자동 완성", description = "실시간 입력값에 따라 DB에 저장된 키워드를 가져옵니다.")
    @GetMapping
    public ApiResponse<AutoCompleteResponseDto> getAutoComplete(@RequestParam String query) {
        log.info("query = {}", query);
        AutoCompleteResponseDto response = autoCompleteService.getAutoComplete(query);
        return ApiResponse.ok(response);
    }
}
