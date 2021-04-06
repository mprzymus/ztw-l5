package pl.mprzymus.ztwl5.book_borrow;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pl.mprzymus.ztwl5.books.model.BookDto;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record BorrowDto(Integer id,
                        LocalDateTime borrowDate,
                        LocalDateTime returnDate,
                        BookDto bookDto)
{}
