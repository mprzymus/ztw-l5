package pl.mprzymus.ztwl5.authors.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mprzymus.ztwl5.authors.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
