package com.EstatePass.exceptions;

public class InvalidGatePassException extends RuntimeException{

    public InvalidGatePassException(String message){
        super(message);
    }
}
