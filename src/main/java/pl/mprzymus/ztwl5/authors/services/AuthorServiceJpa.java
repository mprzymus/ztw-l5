package pl.mprzymus.ztwl5.authors.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.authors.model.AuthorDto;
import pl.mprzymus.ztwl5.authors.model.AuthorListDto;
import pl.mprzymus.ztwl5.authors.repositories.AuthorRepository;
import pl.mprzymus.ztwl5.errors.AuthorNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceJpa implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorListDto getAuthors() {
        var authors = authorRepository.findAll()
                .stream()
                .map(AuthorConverter::authorToAuthorDto)
                .collect(Collectors.toList());
        return new AuthorListDto(authors);
    }

    @Override
    public AuthorDto getAuthor(int id) {
        Author author = tryGetAuthor(id);
        return AuthorConverter.authorToAuthorDto(author);
    }

    private Author tryGetAuthor(int id) {
        return authorRepository.findById(id).orElseThrow(AuthorNotFoundException::new);
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        var author = new Author();
        author.setFirstName(authorDto.firstName());
        author.setLastName(authorDto.lastName());
        setBooks(authorDto, author);
        var saved = authorRepository.save(author);
        return AuthorConverter.authorToAuthorDto(saved);
    }

    private void setBooks(AuthorDto authorDto, Author author) {
        if (authorDto.books() != null) {
            var books = authorDto.books().stream()
                    .map(book -> AuthorConverter.authorsBookDtoToBook(book, author))
                    .collect(Collectors.toSet());
            author.setBooks(books);
        }
    }

    @Override
    public AuthorDto updateAuthor(AuthorDto author) {
        var authorDb = tryGetAuthor(author.id());
        authorDb.setFirstName(author.firstName());
        authorDb.setLastName(author.lastName());
        return AuthorConverter.authorToAuthorDto(authorRepository.save(authorDb));
    }

    @Override
    public Optional<Author> findById(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return authorRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        try {
            authorRepository.deleteById(id);
        }catch (Exception e) {
            throw new AuthorNotFoundException();
        }
    }
}
