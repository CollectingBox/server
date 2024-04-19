package contest.collectingbox.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
        ErrorResponse errorResponse = makeValidErrorResponse(e.getBindingResult());
        return ResponseEntity.status(errorResponse.getStatus()).body(errorResponse);

    }

    private ErrorResponse makeValidErrorResponse(BindingResult bindingResult) {
        ErrorCode errorCode = null;
        String message = bindingResult.getFieldError().getDefaultMessage();
        if (bindingResult.hasErrors()) {
            String bindResultCode = bindingResult.getFieldError().getCode();
            switch (bindResultCode) {
                case "NotNull":
                    errorCode = ErrorCode.NOT_EMPTY_VALUE;
                    break;
            }
        }
        return ErrorResponse.from(errorCode, message);
    }

}
