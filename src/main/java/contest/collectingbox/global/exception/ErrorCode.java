package contest.collectingbox.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400
    NOT_SELECTED_TAG(BAD_REQUEST, "수거함 태그는 반드시 한 개 이상 설정해야 합니다.");

    // 404

    // 409

    // 500

    private final HttpStatus httpStatus;
    private final String message;
}