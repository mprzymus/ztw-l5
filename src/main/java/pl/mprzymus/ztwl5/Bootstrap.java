package pl.mprzymus.ztwl5;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.authors.repositories.AuthorRepository;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.services.BookCrudService;

import java.util.HashSet;

@Component
@Slf4j
@RequiredArgsConstructor
public class Bootstrap implements CommandLineRunner {

    private final BookCrudService bookService;
    private final AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {

        var author1 = new Author(null, "Henryk", "Sienkiewicz", new HashSet<>());
        var author2 = new Author(null, "Stanisław", "Reymont", new HashSet<>());
        var author3 = new Author(null, "Adam", "Mickiewicz", new HashSet<>());

        log.info("Adding default objects");
        var book1 = Book.builder().tittle("Potop").author(author1).pages(936).build();
        var book2 = Book.builder().tittle("Wesele").author(author2).pages(150).build();
        var book3 = Book.builder().tittle("Dziady").author(author3).pages(292).build();

        author1.getBooks().add(book1);
        author2.getBooks().add(book2);
        author3.getBooks().add(book3);

        authorRepository.save(author1);
        authorRepository.save(author3);
        authorRepository.save(author2);


    }
}
