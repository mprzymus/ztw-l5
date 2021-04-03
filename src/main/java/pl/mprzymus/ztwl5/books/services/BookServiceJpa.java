package pl.mprzymus.ztwl5.books.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.authors.repositories.AuthorRepository;
import pl.mprzymus.ztwl5.authors.services.AuthorService;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.errors.AuthorNotFoundException;
import pl.mprzymus.ztwl5.errors.BookNotFoundException;
import pl.mprzymus.ztwl5.books.repositories.BookRepository;
import pl.mprzymus.ztwl5.books.model.BookDto;
import pl.mprzymus.ztwl5.books.model.BookListDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceJpa implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    @Override
    public BookListDto getBooks() {
        var books = bookRepository.findAll();
        var bookDto = books.stream()
                .map(BookConverter::bookToDto)
                .collect(Collectors.toList());
        return new BookListDto(bookDto);
    }

    @Override
    public BookDto getBook(int id) {
        var book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return BookConverter.bookToDto(book);
    }

    @Override
    @Transactional
    public BookDto updateBook(BookDto requestedBook) {
        if (requestedBook.id() != null) {
            var bookDbOptional = bookRepository.findById(requestedBook.id());
            if (bookDbOptional.isPresent()) {
                var bookDb = bookDbOptional.get();
                changeAuthorAndUpdate(requestedBook, bookDb);
                return BookConverter.bookToDto(bookDb);
            }
            else {
                throw new BookNotFoundException();
            }
        } else {
            throw new BookNotFoundException();
        }
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        return createNewBook(bookDto);
    }

    private void changeAuthorAndUpdate(BookDto requestedBook, Book bookDb) {
        if (isSameAuthor(requestedBook, bookDb)) {
            updateBookData(requestedBook, bookDb);
            var authorDb = bookDb.getAuthor();
            log.info("Updated book: {}, author: {}", bookDb.getId(), authorDb.getId());
            authorRepository.save(authorDb);
        } else {
            var oldAuthor = bookDb.getAuthor();
            oldAuthor.getBooks().remove(bookDb);
            updateBookData(requestedBook, bookDb);
            Author newAuthor;
            if (requestedBook.author().id() != null) {
                newAuthor = tryFindAuthor(requestedBook);
                bookDb.setAuthor(newAuthor);
                newAuthor.getBooks().add(bookDb);
                log.info("Updated book: {}, old author: {}, new author: {}",
                        bookDb.getId(), oldAuthor.getId(), newAuthor.getId());
            } else {
                newAuthor = createNewAuthor(requestedBook, bookDb);
                log.info("Updated book for new author: {}, old author: {}",
                        bookDb.getId(), oldAuthor.getId());
            }
            authorRepository.saveAll(List.of(oldAuthor, newAuthor));
        }
    }

    private boolean isSameAuthor(BookDto requestedBook, Book bookDb) {
        return bookDb.getAuthor().getId().equals(requestedBook.author().id());
    }

    private BookDto createNewBook(BookDto requestedBook) {
        log.info("New book created");
        var book = new Book();
        book.setTittle(requestedBook.tittle());
        book.setPages(requestedBook.pages());
        var authorOptional = authorService.findById(requestedBook.author().id());
        Author author;
        if (authorOptional.isPresent()) {
            author = authorOptional.get();
            author.getBooks().add(book);
        } else {
            author = createNewAuthor(requestedBook, book);
        }
        book.setAuthor(author);
        authorRepository.save(author);
        return BookConverter.bookToDto(book);
    }

    private Author createNewAuthor(BookDto requestedBook, Book bookDb) {
        Author newAuthor;
        newAuthor = new Author();
        var requestedAuthor = requestedBook.author();
        newAuthor.setFirstName(requestedAuthor.firstName());
        newAuthor.setLastName(requestedAuthor.lastName());
        newAuthor.setBooks(Set.of(bookDb));
        bookDb.setAuthor(newAuthor);
        return newAuthor;
    }

    private void updateBookData(BookDto requestedBook, pl.mprzymus.ztwl5.books.model.Book bookDb) {
        bookDb.setPages(requestedBook.pages());
        bookDb.setTittle(requestedBook.tittle());
    }

    private Author tryFindAuthor(BookDto requestedBook) {
        try {
            return authorRepository.findById(requestedBook.author().id()).orElseThrow();
        } catch (Exception e) {
            throw new AuthorNotFoundException();
        }
    }

    @Override
    public void delete(int id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException();
        }
    }
}
