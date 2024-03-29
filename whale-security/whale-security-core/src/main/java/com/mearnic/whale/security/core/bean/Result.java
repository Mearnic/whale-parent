package com.mearnic.whale.security.core.bean;


import java.io.Serializable;

/**
 * 统一返回结果
 */

public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7119210377216793281L;

    private Integer code;

    private String message;

    private T body;

    public Result() {
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.code();
        this.message = resultCode.message();
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public Result<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public Result<T> message(String message) {
        this.message = message;
        return this;
    }

    public Result<T> body(T body) {
        this.body = body;
        return this;
    }

    public static <T> Result<T> success() {
        return new Result<>(CommonCode.SUCCESS);
    }

    public static <T> Result<T> isSuccess(Boolean flag) {
        if (flag) {
            return new Result<>(CommonCode.SUCCESS);
        }
        return new Result<>(CommonCode.REQUEST_BAD);
    }

    /**
     * 正常返回
     *
     * @param body 返回的数据
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T body) {
        Result<T> result = new Result<>(CommonCode.SUCCESS);
        result.setBody(body);
        return result;
    }

    /**
     * 错误返回
     *
     * @param resultCode 错误枚举
     * @return
     */
    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<>(resultCode);
    }

    public static <T> Result<T> error(String errMsg) {
        return new Result<>(CommonCode.REQUEST_BAD.code(), errMsg);
    }

    /**
     * 参数丢失错误
     * 自定错误message
     *
     * @param message 错误消息
     * @return
     */
    public static <T> Result<T> paramLost(String message) {
        Result<T> result = new Result<>();
        result.setCode(CommonCode.PARAM_LOST.code());
        result.setMessage(message);
        return result;
    }

    /**
     * 普通错误
     * 自定错误message
     *
     * @param message
     * @return
     */
    public static <T> Result<T> requestBad(String message) {
        Result<T> result = new Result<>();
        result.setCode(CommonCode.REQUEST_BAD.code());
        result.setMessage(message);
        return result;
    }
}
