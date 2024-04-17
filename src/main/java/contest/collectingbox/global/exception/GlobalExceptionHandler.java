package contest.collectingbox.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CollectingBoxException.class)
    public ErrorResponse handleCollectingBoxException(CollectingBoxException e) {
        log.error("exception message = {}", e.getMessage());
        return ErrorResponse.from(e.getErrorCode());
    }
}
