package pl.mprzymus.ztwl5.errors;

import java.util.NoSuchElementException;

public class NoBooksToBorrowException extends NoSuchElementException {
    public NoBooksToBorrowException() {
    }

    public NoBooksToBorrowException(String s, Throwable cause) {
        super(s, cause);
    }

    public NoBooksToBorrowException(Throwable cause) {
        super(cause);
    }

    public NoBooksToBorrowException(String s) {
        super(s);
    }
}
