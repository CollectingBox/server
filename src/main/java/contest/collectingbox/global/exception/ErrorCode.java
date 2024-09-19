package contest.collectingbox.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400
    NOT_SELECTED_TAG(BAD_REQUEST, "수거함 태그는 반드시 한 개 이상 설정해야 합니다."),
    INVALID_REVIEW_CONTENT(BAD_REQUEST, "올바르지 않는 리뷰 내용입니다."),
    INVALID_BEAN(BAD_REQUEST, "유효하지 않은 데이터입니다."),
    MISSING_REQUEST_PARAM(BAD_REQUEST, "필수 요청 파라미터가 존재하지 않습니다."),
    MISMATCH_REQUEST_PARAM(BAD_REQUEST, "요청 파라미터가 유효하지 않습니다."),
    OUT_OF_RANGE_LONGITUDE(BAD_REQUEST, "경도는 -180 이상 180 이하의 값이어야 합니다."),
    OUT_OF_RANGE_LATITUDE(BAD_REQUEST, "위도는 -90 이상 90 이하의 값이어야 합니다."),
    NOT_NULL_LONGITUDE(BAD_REQUEST, "경도 값은 필수입니다."),
    NOT_NULL_LATITUDE(BAD_REQUEST, "위도 값은 필수입니다."),

    // 404
    NOT_FOUND_COLLECTING_BOX(NOT_FOUND, "해당 수거함이 존재하지 않습니다."),

    // 임시
    NOT_FOUND_TAG(NOT_FOUND, "해당 이름과 일치하는 수거함 태그가 없습니다."),

    // 500
    UNEXPECTED_ERROR_EXTERNAL_API(INTERNAL_SERVER_ERROR, "외부 API 호출 시 알 수 없는 예외가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
