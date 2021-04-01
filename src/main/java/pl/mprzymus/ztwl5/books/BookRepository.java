package pl.mprzymus.ztwl5.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mprzymus.ztwl5.books.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
