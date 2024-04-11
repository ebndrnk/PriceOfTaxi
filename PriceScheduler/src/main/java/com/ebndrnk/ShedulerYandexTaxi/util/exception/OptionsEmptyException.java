package com.ebndrnk.ShedulerYandexTaxi.util.exception;

public class OptionsEmptyException extends RuntimeException{
    public OptionsEmptyException(){
        super("Options are empty!");
    }
}
