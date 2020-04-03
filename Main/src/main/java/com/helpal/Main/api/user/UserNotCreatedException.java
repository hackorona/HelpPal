package com.helpal.Main.api.user;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException(String message) {
        super("Could not create user: " + message);
    }
}