package com.estatepass.exceptions;

public class ResidentDoesNotExistException extends RuntimeException{

    public ResidentDoesNotExistException(String message){
        super (message);
    }
}
