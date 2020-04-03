package com.helpal.Main.api.user;

public class UserNotFoundException extends RuntimeException {

    UserNotFoundException(String id) {
        super("Could not find user " + id);
    }
}