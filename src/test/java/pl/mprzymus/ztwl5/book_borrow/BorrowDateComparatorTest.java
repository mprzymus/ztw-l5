package pl.mprzymus.ztwl5.book_borrow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mprzymus.ztwl5.books.model.Book;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BorrowDateComparatorTest {

    private BorrowDateComparator comparator;

    @BeforeEach
    void setUp() {
        comparator = new BorrowDateComparator();
    }

    @Test
    void compareTest() {
        var book = new Book();

        var b1 = new BookBorrow();
        b1.setBook(book);
        b1.setBorrowDate(LocalDate.now());

        var b2 = new BookBorrow();
        b2.setBook(book);
        b2.setBorrowDate(LocalDate.of(1999, 5, 2));

        assertTrue(comparator.compare(b1, b2) > 0);
    }
}