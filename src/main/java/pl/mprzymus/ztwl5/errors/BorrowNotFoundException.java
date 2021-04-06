package pl.mprzymus.ztwl5.errors;

import java.util.NoSuchElementException;

public class BorrowNotFoundException extends NoSuchElementException {
    public BorrowNotFoundException() {
    }

    public BorrowNotFoundException(String s, Throwable cause) {
        super(s, cause);
    }

    public BorrowNotFoundException(Throwable cause) {
        super(cause);
    }

    public BorrowNotFoundException(String s) {
        super(s);
    }
}
