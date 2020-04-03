package com.helpal.Main.api.request;

public class RequestNotFoundException extends RuntimeException {

    RequestNotFoundException(String id) {
        super("Could not find request " + id);
    }
}