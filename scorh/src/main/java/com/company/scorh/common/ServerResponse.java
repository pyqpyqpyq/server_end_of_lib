package com.company.scorh.common;



public class ServerResponse<T> {

    private int status;
    private String message;
    private T data;

    private ServerResponse(int status, String message,T data) {
        this.status = status;
        this.message=message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createResponse(int status, T data) {
        return new ServerResponse<>(status,null,data);
    }
    public static <T> ServerResponse<T> createResponse(int status, String message) {
        return new ServerResponse<>(status,message,null);
    }


}
