package study.mymemo.exception;

public enum ErrorCode {

    INVALID_REQUEST("400", "요청 인자가 잘못되었습니다."),
    DUPLICATE_TITLE("400", "이미 존재하는 제목입니다."),

    NOT_FOUND_MEMO("404", "메모를 찾을 수 없습니다."),
    NOT_FOUND_CATEGORY("404", "카테고리를 찾을 수 없습니다."),

    UNKNOWN_ERROR("500", "알 수 없는 오류가 발생했습니다."),
    ;

    private final String errorCode;
    private final String errorMessage;

    ErrorCode(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
