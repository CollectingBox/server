package contest.collectingbox.global.exception;

import static contest.collectingbox.global.exception.ErrorCode.INVALID_BEAN;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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

}
