package contest.collectingbox.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private HttpStatus status;
    private String message;

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getHttpStatus(), errorCode.getMessage());
    }

    public static ErrorResponse from(ErrorCode errorCode, String message){
        return new ErrorResponse(errorCode.getHttpStatus(), message);
    }
}
