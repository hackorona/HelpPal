package com.helpal.Main.api.request;

public class RequestNotFoundException extends RuntimeException {

    RequestNotFoundException(Long id) {
        super("Could not find request " + id);
    }
}