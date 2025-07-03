package NewsApp.exception;


import java.time.Instant;

public class ErrorResponse {
    private int statusCode;
    private String message;

    // Конструктор по умолчанию (необязателен, но полезен для Spring/Jackson)
    public ErrorResponse() {}

    // Конструктор с параметрами
    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    // Геттеры и сеттеры (обязательно!)
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}