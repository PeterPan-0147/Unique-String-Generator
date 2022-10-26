package com.edrone.stringgenerator.exceptions;

import lombok.Getter;

@Getter
public class TaskNotFoundException extends RuntimeException{

    private final String message;

    public TaskNotFoundException(String message) {
        this.message = message;
    }
}
