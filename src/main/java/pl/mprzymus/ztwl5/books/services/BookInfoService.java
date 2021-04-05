package pl.mprzymus.ztwl5.books.services;

import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.books.model.Book;

import java.util.List;

public interface BookInfoService {
    List<Book> findBookByTittleAndAuthor(String tittle, Author author);
    Book findNotBorrowedItemByTittleAndAuthor(String tittle, Author author);
    Book updateBook(Book book);
}
