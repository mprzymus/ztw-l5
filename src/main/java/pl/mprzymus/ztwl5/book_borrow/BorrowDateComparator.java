package pl.mprzymus.ztwl5.book_borrow;

import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class BorrowDateComparator implements Comparator<BookBorrow> {
    @Override
    public int compare(BookBorrow bookBorrow, BookBorrow t1) {
        if (!bookBorrow.getBook().equals(t1.getBook())) {
            throw new IllegalArgumentException("Cannot compare borrows of 2 different books");

        }
        return bookBorrow.getBorrowDate().compareTo(t1.getBorrowDate());
    }
}
