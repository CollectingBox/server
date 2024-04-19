package contest.collectingbox.global.exception;

import static contest.collectingbox.global.exception.ErrorCode.ILLEGAL_ARGUMENT;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CollectingBoxException.class)
    public ResponseEntity<ErrorResponse> handleCollectingBoxException(CollectingBoxException e) {
        ErrorResponse errorResponse = ErrorResponse.from(e.getErrorCode());
        log.error("exception message = {}", e.getMessage());
        return ResponseEntity.status(errorResponse.getHttpStatus()).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse handleIllegalArgument(IllegalArgumentException e){
        log.error("exception message = {}", e.getMessage());
        return ErrorResponse.from(ILLEGAL_ARGUMENT);
    }
}
