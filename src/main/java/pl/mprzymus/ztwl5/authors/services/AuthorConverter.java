package pl.mprzymus.ztwl5.authors.services;

import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.authors.model.AuthorDto;
import pl.mprzymus.ztwl5.authors.model.AuthorsBookDto;
import pl.mprzymus.ztwl5.authors.model.BookAuthorDto;
import pl.mprzymus.ztwl5.books.model.Book;

import java.util.stream.Collectors;

public class AuthorConverter {
    public static BookAuthorDto authorToBookAuthorDto(Author author) {
        return new BookAuthorDto(author.getId(), author.getFirstName(), author.getLastName());
    }

    public static AuthorDto AuthorToAuthorDto(Author author) {
        var books = author.getBooks().stream()
                .map(AuthorConverter::bookToAuthorsBookDto)
                .collect(Collectors.toList());
        return new AuthorDto(author.getId(), author.getFirstName(), author.getLastName(), books);

    }

    public static AuthorsBookDto bookToAuthorsBookDto(Book book) {
        return new AuthorsBookDto(book.getId(), book.getTittle(), book.getPages());
    }

    public static Book authorsBookDtoToBook(AuthorsBookDto authorsBookDto, Author author) {
        return Book.builder()
                .id(authorsBookDto.id())
                .tittle(authorsBookDto.tittle())
                .author(author)
                .pages(authorsBookDto.pages())
                .build();
    }
}
