package pl.mprzymus.ztwl5.errors;

import java.util.NoSuchElementException;

public class BookNotFoundException extends NoSuchElementException {
    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }

    public BookNotFoundException(Throwable cause) {
        super(cause);
    }

    public BookNotFoundException(String s) {
        super(s);
    }
}
