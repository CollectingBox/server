package contest.collectingbox.global.exception;

import lombok.Getter;

@Getter
public class CollectingBoxException extends RuntimeException {
    private final ErrorCode errorCode;

    public CollectingBoxException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
