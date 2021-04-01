package pl.mprzymus.ztwl5.books.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.mprzymus.ztwl5.books.BookNotFoundException;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.model.BookListDto;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("mockup")
public class BookServiceMockup implements BookService {

    private static int nextId = 0;
    private final List<Book> booksRepo = new ArrayList<>();

    @Override
    public BookListDto getBooks() {
        return new BookListDto(booksRepo);
    }

    @Override
    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(book -> book.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Book createOrUpdateBook(Book requestedBook) {
        Book saved;
        if (requestedBook.getId() != null) {
            saved = updateBook(requestedBook);
        }
        else {
            saved = createBook(requestedBook);
        }
        return saved;
    }

    @Override
    public void delete(int id) {
        var toDelete = booksRepo.stream()
                .filter(book -> book.getId().equals(id))
                .findAny()
                .orElseThrow(BookNotFoundException::new);
        booksRepo.remove(toDelete);
    }

    private Book updateBook(Book requestedBook) {
        Book saved;
        var index = booksRepo.stream()
                .filter(book -> book.getId().equals(requestedBook.getId()))
                .findAny()
                .map(booksRepo::indexOf);
        if (index.isPresent()) {
            booksRepo.set(index.get(), requestedBook);
            saved = requestedBook;
        }
        else {
            saved = createBook(requestedBook);
        } return saved;
    }

    private Book createBook(Book requestedBook) {
        Book saved;
        var toSave = new Book(nextId++, requestedBook.getTittle(), requestedBook.getAuthor(), requestedBook.getPages());
        booksRepo.add(toSave);
        saved = toSave;
        return saved;
    }
}
