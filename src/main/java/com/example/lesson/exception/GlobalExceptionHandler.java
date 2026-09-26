package com.example.lesson.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice // 모든 컨트롤러의 예외를 여기서 처리
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class) // 대상을 찾지 못했을 때
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class) // @Valid 검증에 실패했을 때
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException exception) {

        String message = "입력값이 올바르지 않습니다.";
        FieldError error = exception.getBindingResult().getFieldError();

        if (error != null && error.getDefaultMessage() != null) { //error가 null이 아니고 오류 문구도 있으면 message에 저장
            message = error.getDefaultMessage();
        }

        return ResponseEntity.badRequest()
                .body(Map.of("message", message));
    }
}