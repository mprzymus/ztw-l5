package pl.mprzymus.ztwl5.books.services;

import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.model.BookListDto;

public interface BookService {
    BookListDto getBooks();
    Book getBook(int id);
    Book createOrUpdateBook(Book requestedBook);
    void delete(int id);
}
