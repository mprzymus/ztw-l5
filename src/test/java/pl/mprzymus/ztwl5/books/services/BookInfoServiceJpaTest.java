package pl.mprzymus.ztwl5.books.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.book_borrow.BookBorrow;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.repositories.BookRepository;
import pl.mprzymus.ztwl5.errors.NoBooksToBorrowException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookInfoServiceJpaTest {

    public static final int ID = 1;
    @InjectMocks
    private BookInfoServiceJpa bookInfoService;

    @Mock
    private BookRepository bookRepository;

    @Test
    void bookIsNotBorrowedTest() {
        Book book = createBook(createReturnedBorrow);

        when(bookRepository.findAllByTittleAndAuthor(anyString(), any())).thenReturn(List.of(book));

        var found = bookInfoService.findNotBorrowedItemByTittleAndAuthor("", new Author());

        assertEquals(book.getId(), found.getId());
    }

    @Test
    void bookIsBorrowedTest() {
        Book book = createBook(createNotReturnedBorrow);

        when(bookRepository.findAllByTittleAndAuthor(anyString(), any())).thenReturn(List.of(book));

        assertThrows(NoBooksToBorrowException.class,
                () -> bookInfoService.findNotBorrowedItemByTittleAndAuthor("", new Author()));
    }

    private Book createBook(Function<Book, BookBorrow> createNewestBorrow) {
        var book = new Book();
        book.setId(ID);

        BookBorrow b1 = createNewestBorrow.apply(book);

        BookBorrow b2 = createNotReturnedBorrow(book, LocalDateTime.of(1999, 5, 2, 2, 2));

        book.setBorrowHistory(Set.of(b1, b2));
        return book;
    }

    private final Function<Book, BookBorrow> createNotReturnedBorrow = book ->
        createNotReturnedBorrow(book, LocalDateTime.now());

    private final Function<Book, BookBorrow> createReturnedBorrow = book -> {
        var borrow = createNotReturnedBorrow(book, LocalDateTime.now());
        borrow.setReturnDate(LocalDateTime.MAX);
        return borrow;
    };


    private BookBorrow createNotReturnedBorrow(Book book, LocalDateTime now) {
        var b1 = new BookBorrow();
        b1.setBook(book);
        b1.setBorrowDate(now);
        return b1;
    }
}