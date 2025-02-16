package com.productmangement.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {

    private T data;
    private String message;
    private String status;

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, "SUCCESS");
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(null, message, "ERROR");
    }

}
