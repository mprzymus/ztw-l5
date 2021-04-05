package pl.mprzymus.ztwl5.book_borrow;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pl.mprzymus.ztwl5.books.model.BookDto;

import java.time.LocalDate;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record BorrowDto(Integer id,
                        LocalDate borrowDate,
                        LocalDate returnDate,
                        BookDto bookDto)
{}
