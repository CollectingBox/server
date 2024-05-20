package contest.collectingbox.global.exception;

public class CollectingBoxException extends RuntimeException {
    private final ErrorCode errorCode;

    public CollectingBoxException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode errorCode() {
        return errorCode;
    }

    public String message() {
        return errorCode.getMessage();
    }
}
