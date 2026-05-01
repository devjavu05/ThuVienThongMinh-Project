package com.devjavu.standardProject.exception;

import lombok.Getter;

@Getter
public class AppException extends RuntimeException {
    private ErrorCode errorCode;
    public  AppException(ErrorCode errorCode){
        super(errorCode.name());
        this.errorCode=errorCode;
    }
}
