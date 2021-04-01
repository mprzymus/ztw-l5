package pl.mprzymus.ztwl5.books.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mprzymus.ztwl5.authors.AuthorRepository;
import pl.mprzymus.ztwl5.books.BookNotFoundException;
import pl.mprzymus.ztwl5.books.BookRepository;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.model.BookListDto;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceJpa implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public BookListDto getBooks() {
        return new BookListDto(bookRepository.findAll().stream().collect(Collectors.toList()));
    }

    @Override
    public Book getBook(int id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    @Transactional
    public Book createOrUpdateBook(Book requestedBook) {
        authorRepository.save(requestedBook.getAuthor());
        return bookRepository.save(requestedBook);
    }

    @Override
    public void delete(int id) {
        try {
            bookRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException();
        }
    }
}
