package pl.mprzymus.ztwl5.authors.services;

import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.authors.model.AuthorDto;
import pl.mprzymus.ztwl5.authors.model.AuthorListDto;

import java.util.Optional;

public interface AuthorService {
    AuthorListDto getAuthors();

    AuthorDto getAuthor(int id);

    AuthorDto createAuthor(AuthorDto author);

    AuthorDto updateAuthor(AuthorDto author);

    Optional<Author> findById(Integer id);

    void delete(int id);
}
