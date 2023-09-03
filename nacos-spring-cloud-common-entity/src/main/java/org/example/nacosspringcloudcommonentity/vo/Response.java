package org.example.nacosspringcloudcommonentity.vo;


import lombok.Data;
import org.example.nacosspringcloudcommonentity.constant.Const;

@Data
public class Response<T> {
    /* 状态码 */
    private Integer code;
    /* 提示消息 */
    private String message;
    /* 具体返回的数据 */
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Response(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private Response(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }

    private Response() {

    }

    /**
     * 返回成功Response对象
     *
     * @param successMessage 成功提示信息
     * @param data           需要返回的数据
     * @return 成功信息
     */
    public static <T> Response<T> createSuccessResponse(String successMessage, T data) {
        return new Response<>(Const.SUCCESS, successMessage, data);
    }

    /**
     * 只返回成功消息, 默认成功状态码
     *
     * @param successMessage 成功消息
     * @return Response对象
     */
    public static <T> Response<T> createSuccessResponse(String successMessage) {
        return new Response<>(Const.SUCCESS, successMessage);
    }

    /**
     * 只返回成功消息, 默认成功状态码
     *
     * @param data 返回的数据
     * @return Response对象
     */
    public static <T> Response<T> createSuccessResponse(T data) {
        return new Response<>(Const.SUCCESS, "成功", data);
    }

    /**
     * 返回错误Response对象
     *
     * @param errorMessage 错误信息
     * @return 错误信息
     */
    public static <T> Response<T> createErrorResponse(String errorMessage) {
        return new Response<>(Const.ERROR, errorMessage);
    }

    /**
     * 返回错误Response对象
     *
     * @param errorMessage 错误信息
     * @return 错误信息
     */
    public static <T> Response<T> createErrorResponse(String errorMessage,T data) {
        return new Response<>(Const.ERROR, errorMessage,data);
    }

    /**
     * 返回未登录状态码
     * @param message 提示信息
     * @return Response
     */
    public static <T> Response<T> createUnLoginResponse(String message){
        return new Response<>(Const.UN_LOGIN,message);
    }
}

