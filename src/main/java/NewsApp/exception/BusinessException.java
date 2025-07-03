package NewsApp.exception;

// Базовое исключение для всех бизнес-ошибок
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private String customMessage; // Опционально

    public BusinessException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }

    // Геттеры

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public String getCustomMessage() {
        return customMessage;
    }

}