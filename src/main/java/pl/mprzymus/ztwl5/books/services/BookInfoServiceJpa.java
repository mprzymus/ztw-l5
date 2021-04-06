package pl.mprzymus.ztwl5.books.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.book_borrow.BorrowDateComparator;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.repositories.BookRepository;
import pl.mprzymus.ztwl5.errors.BookNotFoundException;
import pl.mprzymus.ztwl5.errors.NoBooksToBorrowException;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookInfoServiceJpa implements BookInfoService {

    private final BookRepository bookRepository;
    private final BorrowDateComparator borrowDateComparator;

    @Override
    public List<Book> findBookByTittleAndAuthor(String tittle, Author author) {
        return bookRepository.findAllByTittleAndAuthor(tittle, author);
    }

    @Override
    public Book findNotBorrowedItemByTittleAndAuthor(String tittle, Author author) {
        var items = findBookByTittleAndAuthor(tittle, author);
        return items.stream()
                .filter(this::isBookNotBorrowed)
                .findAny()
                .orElseThrow(NoBooksToBorrowException::new);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    private boolean isBookNotBorrowed(Book book) {
        var borrowHistory = book.getBorrowHistory();
        log.info("empty: {}, size: {}", borrowHistory.isEmpty(), borrowHistory.size());
        return borrowHistory.isEmpty() || borrowHistory.stream()
                .max(borrowDateComparator)
                .filter(bookBorrow -> bookBorrow.getReturnDate() != null) // not yet returned
                .isPresent();


    }
}
