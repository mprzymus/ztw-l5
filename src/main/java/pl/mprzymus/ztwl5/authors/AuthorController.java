package pl.mprzymus.ztwl5.authors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.ztwl5.authors.AuthorDto;
import pl.mprzymus.ztwl5.authors.AuthorListDto;
import pl.mprzymus.ztwl5.authors.AuthorService;

@RequestMapping("/api/authors")
@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public AuthorListDto getAuthors() {
        return authorService.getAuthors();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public AuthorDto getAuthor(@PathVariable int id) {
        return authorService.getAuthor(id);
    }

    @PostMapping
    public AuthorDto createNewAuthor(@RequestBody AuthorDto author) {
        return authorService.createAuthor(author);
    }

    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@RequestBody AuthorDto author, @PathVariable Integer id) {
        //var toSave = new AuthorDto(id, author.tittle(), author.author(), author.pages());
        return authorService.updateAuthor(author);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable int id) {
        authorService.delete(id);
    }
}
