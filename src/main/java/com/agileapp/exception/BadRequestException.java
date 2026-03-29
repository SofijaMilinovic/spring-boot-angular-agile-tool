package com.agileapp.exception;

public class BadRequestException extends RuntimeException{

    public BadRequestException(String message){
        super(message); //poziva konstruktor roditeljske klase koji zna da primi poruku i obradi izuzetak
    }
}
