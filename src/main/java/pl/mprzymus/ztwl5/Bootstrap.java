package pl.mprzymus.ztwl5;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mprzymus.ztwl5.authors.Author;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.services.BookService;

import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final BookService bookService;

    @Override
    public void run(String... args) throws Exception {

        var author1 = new Author(1, "Henryk", "Sienkiewicz", Set.of());
        var author2 = new Author(2, "Stanisław", "Reymont", Set.of());
        var author3 = new Author(3, "Adam", "Mickiewicz", Set.of());

        log.info("Adding default objects");
        bookService.createOrUpdateBook(new Book(1, "Potop", author1, 936));
        bookService.createOrUpdateBook(new Book(2, "Wesele", author2, 150));
        bookService.createOrUpdateBook(new Book(3, "Dziady", author3, 292));
    }
}
