package com.example.news.exception;


public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException() {
        super(ErrorCode.ENTITY_NOT_FOUND); // Жёстко задаём код ошибки
    }

    // Или с кастомным сообщением
    public ResourceNotFoundException(String entityName) {
        super(ErrorCode.ENTITY_NOT_FOUND, entityName + " не найдено"); // Добавляем сообщение
    }
}
