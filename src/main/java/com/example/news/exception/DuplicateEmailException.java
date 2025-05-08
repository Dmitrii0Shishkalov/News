package com.example.news.exception;


public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException() {
        super(ErrorCode.EMAIL_ALREADY_EXISTS, "Это электронная почта,  уже используется");
    }

    // Опционально: конструктор с кастомным сообщением
    public DuplicateEmailException(String message) {
        super(ErrorCode.EMAIL_ALREADY_EXISTS, message);
    }
}