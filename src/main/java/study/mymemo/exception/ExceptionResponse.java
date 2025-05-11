package study.mymemo.exception;

public class ExceptionResponse {
    private final String errorCode;
    private final String errorMessage;
    private final String timestamp;

    public ExceptionResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
