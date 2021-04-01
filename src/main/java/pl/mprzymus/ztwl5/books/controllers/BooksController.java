package pl.mprzymus.ztwl5.books.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mprzymus.ztwl5.books.model.Book;
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
    public Book getBook(@PathVariable int id) {
        return bookService.getBook(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.createOrUpdateBook(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookService.delete(id);
    }
}
