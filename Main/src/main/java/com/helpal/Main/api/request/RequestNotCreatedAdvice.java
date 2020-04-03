package com.helpal.Main.api.request;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RequestNotCreatedAdvice {

    @ResponseBody
    @ExceptionHandler(RequestNotCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String requestNotCreatedHandler(RequestNotCreatedException ex) {
        return ex.getMessage();
    }
}