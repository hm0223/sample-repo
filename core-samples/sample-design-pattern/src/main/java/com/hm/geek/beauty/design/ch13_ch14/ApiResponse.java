package com.hm.geek.beauty.design.ch13_ch14;

import lombok.Data;

/**
 * ApiResponse.
 *
 * @author huwenfeng
 */
@Data
public class ApiResponse<T> {

    public static final String SUCCESS_CODE = "200";
    public static final String SUCCESS_MESSAGE = "成功";
    public static final String FAILED_CODE = "500";
    public static final String FAILED_MESSAGE = "失败";

    private String code;
    private String message;
    private T data;
    private boolean success;

    public ApiResponse() {
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T t) {
        return new ApiResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE, t);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>(SUCCESS_CODE, SUCCESS_MESSAGE);
    }

    public static <T> ApiResponse<T> error(T t) {
        return new ApiResponse<>(FAILED_CODE, FAILED_MESSAGE, t);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(FAILED_CODE, message);
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, message);
    }

    public boolean isSuccess() {
        return ApiResponse.SUCCESS_CODE.equals(this.code);
    }
    
}
