package com.example.hackthone_it211.exception;

import com.example.hackthone_it211.model.dto.response.ApiDataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiDataResponse<List<Map<String, String>>>> handleValidationExceptions(
            MethodArgumentNotValidException ex
    ) {
        List<Map<String, String>> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            Map<String, String> error = new LinkedHashMap<>();
            error.put("field", fieldError.getField());
            error.put("message", fieldError.getDefaultMessage());
            errors.add(error);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiDataResponse.error("Dữ liệu gửi lên không hợp lệ", errors, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiDataResponse.error(ex.getMessage(), null, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiDataResponse<Object>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiDataResponse.error("Dữ liệu JSON không hợp lệ hoặc enum status không đúng", null, HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiDataResponse<Object>> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiDataResponse.error("Lỗi hệ thống: " + ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
