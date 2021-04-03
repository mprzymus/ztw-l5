package pl.mprzymus.ztwl5.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotFoundHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({BookNotFoundException.class, AuthorNotFoundException.class})
    public void handleNotFound() {
        // Nothing to do
    }
}