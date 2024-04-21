package contest.collectingbox.global.exception;



import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static contest.collectingbox.global.exception.ErrorCode.INVALID_BEAN;
import static contest.collectingbox.global.exception.ErrorCode.MISMATCH_REQUEST_PARAM;
import static contest.collectingbox.global.exception.ErrorCode.MISSING_REQUEST_PARAM;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CollectingBoxException.class)
    public ResponseEntity<ErrorResponse> handleCollectingBoxException(CollectingBoxException e) {
        ErrorResponse errorResponse = ErrorResponse.from(e.getErrorCode());
        log.error("exception message = {}", e.getMessage());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        log.error("exception message = {}", e.getMessage());
        ErrorResponse errorResponse = ErrorResponse.from(INVALID_BEAN, message);
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleMissingParams(MissingServletRequestParameterException e) {
        log.error("exception message = {}", e.getMessage());
        return ErrorResponse.from(MISSING_REQUEST_PARAM);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleMissingParams(MethodArgumentTypeMismatchException e) {
        log.error("exception message = {}", e.getMessage());
        return ErrorResponse.from(MISMATCH_REQUEST_PARAM);
    }

}
