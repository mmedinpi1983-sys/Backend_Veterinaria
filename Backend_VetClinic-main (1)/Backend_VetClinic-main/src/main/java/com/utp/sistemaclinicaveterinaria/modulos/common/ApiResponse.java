package com.utp.sistemaclinicaveterinaria.modulos.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private Integer count;
    private String error;
    private String errorCode;

    public static <T> ApiResponse<T> Response(String message) {
        return ApiResponse.<T>builder().success(true).message(message).build();
    }

    public static <T> ApiResponse<T> ResponseAn(String message, T data) {
        return ApiResponse.<T>builder().success(true).message(message).data(data).build();
    }

    public static <T> ApiResponse<T> ResponseList(String message, T data, Integer count) {
        return ApiResponse.<T>builder().success(true).message(message).data(data).count(count).build();
    }

    public static <T> ApiResponse<T> fail(String message, String errorCode) {
        return ApiResponse.<T>builder().success(false).message(message).errorCode(errorCode).build();
    }

    public static <T> ApiResponse<T> error(String message, String error) {
        return ApiResponse.<T>builder().success(false).message(message).error(error).build();
    }
}
