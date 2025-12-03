package org.example.filestoring.application.exception;

public class HomeworkNotFoundException extends RuntimeException{
    public HomeworkNotFoundException() {
        super("Homework not found");
    }
}
