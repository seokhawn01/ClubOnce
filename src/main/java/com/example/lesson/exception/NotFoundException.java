package com.example.lesson.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message); // 
    }
}