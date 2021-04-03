package pl.mprzymus.ztwl5.authors;

import pl.mprzymus.ztwl5.books.model.BookListDto;

public interface AuthorService {
    AuthorListDto getAuthors();

    AuthorDto getAuthor(int id);

    AuthorDto createAuthor(AuthorDto author);

    AuthorDto updateAuthor(AuthorDto author);

    void delete(int id);
}
