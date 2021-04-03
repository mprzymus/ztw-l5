package pl.mprzymus.ztwl5.books.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pl.mprzymus.ztwl5.authors.BookAuthorDto;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) //annotation required for records
public record BookDto(Integer id, String tittle, BookAuthorDto author, int pages) {
}
