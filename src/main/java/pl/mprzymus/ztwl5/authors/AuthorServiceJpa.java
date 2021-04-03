package pl.mprzymus.ztwl5.authors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mprzymus.ztwl5.errors.AuthorNotFoundException;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceJpa implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorListDto getAuthors() {
        var authors = authorRepository.findAll()
                .stream()
                .map(AuthorConverter::AuthorToAuthorDto)
                .collect(Collectors.toList());
        return new AuthorListDto(authors);
    }

    @Override
    public AuthorDto getAuthor(int id) {
        Author author = tryGetAuthor(id);
        return AuthorConverter.AuthorToAuthorDto(author);
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
        return AuthorConverter.AuthorToAuthorDto(saved);
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
        return AuthorConverter.AuthorToAuthorDto(authorRepository.save(authorDb));
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