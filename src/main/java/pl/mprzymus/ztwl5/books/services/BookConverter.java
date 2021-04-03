package pl.mprzymus.ztwl5.books.services;

import pl.mprzymus.ztwl5.authors.services.AuthorConverter;
import pl.mprzymus.ztwl5.books.model.Book;
import pl.mprzymus.ztwl5.books.model.BookDto;

public class BookConverter {
    public static BookDto bookToDto(Book book) {
        var authorDto = AuthorConverter.authorToBookAuthorDto(book.getAuthor());
        return new BookDto(book.getId(), book.getTittle(), authorDto, book.getPages());
    }

    public static Book dtoToBook(BookDto dto) {
        Book book = new Book();
        book.setId(dto.id());
        book.setPages(dto.pages());
        book.setTittle(dto.tittle());
        return book;
    }
}
