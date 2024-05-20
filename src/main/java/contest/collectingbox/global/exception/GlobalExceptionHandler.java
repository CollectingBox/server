package contest.collectingbox.global.exception;

import contest.collectingbox.global.common.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static contest.collectingbox.global.exception.ErrorCode.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CollectingBoxException.class)
    public ApiResponse<Object> handleCollectingBoxException(CollectingBoxException e) {
        return ApiResponse.error(e.errorCode(), e.message());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> methodValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.error(INVALID_BEAN, message);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiResponse<Object> handleMissingParams(MissingServletRequestParameterException e) {
        return ApiResponse.error(MISSING_REQUEST_PARAM, MISSING_REQUEST_PARAM.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ApiResponse<Object> handleMissingParams(MethodArgumentTypeMismatchException e) {
        return ApiResponse.error(MISMATCH_REQUEST_PARAM, MISSING_REQUEST_PARAM.getMessage());
    }
}
