package pl.mprzymus.ztwl5.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.mprzymus.ztwl5.book_borrow.BorrowDto;

@RestControllerAdvice
public class NotFoundHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({BookNotFoundException.class, AuthorNotFoundException.class})
    public void handleNotFound() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoBooksToBorrowException.class)
    public BorrowDto handleCantBorrow() {
        return null;
    }
}
