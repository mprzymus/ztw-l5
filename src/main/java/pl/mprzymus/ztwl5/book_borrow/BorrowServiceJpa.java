package pl.mprzymus.ztwl5.book_borrow;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.ztwl5.authors.services.AuthorService;
import pl.mprzymus.ztwl5.books.model.BookDto;
import pl.mprzymus.ztwl5.books.services.BookInfoService;
import pl.mprzymus.ztwl5.errors.AuthorNotFoundException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowServiceJpa implements BorrowService {

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
        borrow.setBorrowDate(LocalDate.now());
        book.getBorrowHistory().add(borrow);
        book = bookService.updateBook(book);
        var saved = book.getBorrowHistory()
                .stream()
                .max(borrowDateComparator)
                .get();
        return BorrowConverter.borrowToDto(saved);
    }

    @Override
    public BorrowDto returnBook(BorrowDto borrowData) {
        return null;
    }
}
