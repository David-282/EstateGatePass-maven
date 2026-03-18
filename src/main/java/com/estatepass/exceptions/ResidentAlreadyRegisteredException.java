package com.estatepass.exceptions;

public class ResidentAlreadyRegisteredException extends RuntimeException{

    public ResidentAlreadyRegisteredException (String message){
        super(message);
    }
}
