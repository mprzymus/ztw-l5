package pl.mprzymus.ztwl5.authors.services;

import pl.mprzymus.ztwl5.authors.model.AuthorDto;
import pl.mprzymus.ztwl5.authors.model.AuthorListDto;

public interface AuthorService {
    AuthorListDto getAuthors();

    AuthorDto getAuthor(int id);

    AuthorDto createAuthor(AuthorDto author);

    AuthorDto updateAuthor(AuthorDto author);

    void delete(int id);
}
