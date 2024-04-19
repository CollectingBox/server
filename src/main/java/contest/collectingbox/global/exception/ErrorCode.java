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
    NOT_EMPTY_VALUE(BAD_REQUEST, "필수값이 누락되었습니다."),
    INVALID_REVIEW_CONTENT(BAD_REQUEST, "올바르지 않는 리뷰 내용입니다."),

    // 404
    NOT_FOUND_COLLECTING_BOX(NOT_FOUND, "해당 수거함이 존재하지 않습니다.");

    // 409

    // 500

    private final HttpStatus httpStatus;
    private final String message;
}
