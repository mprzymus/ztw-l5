package pl.mprzymus.ztwl5.books.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.books.model.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByTittleAndAuthor(String tittle, Author author);
}
