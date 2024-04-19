package contest.collectingbox.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400
    NOT_SELECTED_TAG(BAD_REQUEST, "수거함 태그는 반드시 한 개 이상 설정해야 합니다."),
    ILLEGAL_ARGUMENT(BAD_REQUEST, "입력값이 부적절한 인자입니다."),
    // 404
    NOT_FOUND_COLLECTING_BOX(NOT_FOUND, "해당 수거함이 존재하지 않습니다."),

    // 임시
    NOT_FOUND_TAG(NOT_FOUND, "해당 이름과 일치하는 수거함 태그가 없습니다.");
    // 409

    // 500

    private final HttpStatus httpStatus;
    private final String message;
}
