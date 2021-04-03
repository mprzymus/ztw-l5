package pl.mprzymus.ztwl5.books.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.ztwl5.books.model.BookDto;
import pl.mprzymus.ztwl5.books.model.BookListDto;
import pl.mprzymus.ztwl5.books.services.BookService;

@RequestMapping("/api/books")
@RestController
@RequiredArgsConstructor
public class BooksController {
    private final BookService bookService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public BookListDto getBooks() {
        return bookService.getBooks();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public BookDto getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public BookDto createNewBook(@RequestBody BookDto book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@RequestBody BookDto book, @PathVariable Integer id) {
        var toSave = new BookDto(id, book.tittle(), book.author(), book.pages());
        return bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.delete(id);
    }
}
