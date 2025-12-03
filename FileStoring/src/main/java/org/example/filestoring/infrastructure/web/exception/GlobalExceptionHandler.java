package org.example.filestoring.infrastructure.web.exception;

import org.example.filestoring.application.exception.HomeworkNotFoundException;
import org.example.filestoring.application.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleUserNotFound(UserNotFoundException ex) {
        return new ApiError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(HomeworkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleHomeworkNotFound(HomeworkNotFoundException ex) {
        return new ApiError(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleOtherExceptions(Exception ex) {
        return new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal server error"
        );
    }
}