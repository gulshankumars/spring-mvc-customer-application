package com.sunglowsys.resource.util;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
