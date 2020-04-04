package com.helpal.Main.api.request;

public class RequestNotFoundException extends RuntimeException {

    public RequestNotFoundException(String id) {
        super("Could not find request " + id);
    }
}