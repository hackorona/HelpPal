package com.helpal.Main.api.request;

public class RequestNotCreatedException extends RuntimeException {

    public RequestNotCreatedException(String message) {
        super("Could not create request: " + message);
    }
}