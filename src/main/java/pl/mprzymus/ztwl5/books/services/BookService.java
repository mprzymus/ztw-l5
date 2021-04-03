package pl.mprzymus.ztwl5.books.services;

import org.springframework.transaction.annotation.Transactional;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.model.BookDto;
import pl.mprzymus.ztwl5.books.model.BookListDto;

public interface BookService {
    BookListDto getBooks();
    BookDto getBook(int id);
    BookDto updateBook(BookDto requestedBook);
    BookDto createBook(BookDto book);
    void delete(int id);
}
