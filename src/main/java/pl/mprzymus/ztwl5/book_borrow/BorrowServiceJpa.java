package pl.mprzymus.ztwl5.book_borrow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mprzymus.ztwl5.authors.services.AuthorService;
import pl.mprzymus.ztwl5.books.model.BookDto;
import pl.mprzymus.ztwl5.books.services.BookInfoService;
import pl.mprzymus.ztwl5.errors.AuthorNotFoundException;
import pl.mprzymus.ztwl5.errors.BookNotFoundException;
import pl.mprzymus.ztwl5.errors.BorrowNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowServiceJpa implements BorrowService {

    private final BookBorrowRepository bookBorrowRepository;
    private final BookInfoService bookService;
    private final AuthorService authorService;
    private final BorrowDateComparator borrowDateComparator;


    @Override
    public BorrowDto borrowBook(BookDto bookDto) {
        var tittle = bookDto.tittle();
        var author = authorService.findById(bookDto.author().id())
                .orElseThrow(AuthorNotFoundException::new);
        var book = bookService.findNotBorrowedItemByTittleAndAuthor(tittle, author);
        var borrow = new BookBorrow();
        borrow.setBook(book);
        borrow.setBorrowDate(LocalDateTime.now());
        book.getBorrowHistory().add(borrow);
        book = bookService.updateBook(book);
        var saved = book.getBorrowHistory()
                .stream()
                .filter(bookBorrow -> bookBorrow.getReturnDate() == null)
                .max(borrowDateComparator)
                .orElseThrow();
        return BorrowConverter.borrowToDto(saved);
    }

    @Override
    public BorrowDto returnBook(BorrowDto borrowData) {
        var borrow = bookBorrowRepository
                .findById(borrowData.id())
                .orElseThrow(BorrowNotFoundException::new);
        if (borrow.getReturnDate() != null) {
            log.error("Book have already been returned");
            throw new BookNotFoundException();
        }
        borrow.setReturnDate(LocalDateTime.now());
        bookService.updateBook(borrow.getBook());
        return BorrowConverter.borrowToDto(borrow);
    }
}
