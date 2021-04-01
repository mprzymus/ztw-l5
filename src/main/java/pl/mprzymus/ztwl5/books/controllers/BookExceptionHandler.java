package pl.mprzymus.ztwl5.books.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.mprzymus.ztwl5.books.BookNotFoundException;

@RestControllerAdvice
public class BookExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(BookNotFoundException.class)
    public void handleNotFound() {
        // Nothing to do
    }
}
