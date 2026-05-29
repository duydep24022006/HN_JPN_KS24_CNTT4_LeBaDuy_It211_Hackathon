package com.example.hackthone_it211.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiDataResponse<T> {
    private Boolean success;
    private String message;
    private T data;
    private Integer statusCode;
    private HttpStatus status;

    public static <T> ApiDataResponse<T> success(String message, T data, HttpStatus status) {
        return ApiDataResponse.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .statusCode(status.value())
                .status(status)
                .build();
    }

    public static <T> ApiDataResponse<T> error(String message, T data, HttpStatus status) {
        return ApiDataResponse.<T>builder()
                .success(false)
                .message(message)
                .data(data)
                .statusCode(status.value())
                .status(status)
                .build();
    }
}
