package pl.mprzymus.ztwl5.book_borrow;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mprzymus.ztwl5.books.model.Book;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BookBorrow {
    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookBorrow that = (BookBorrow) o;
        return Objects.equals(id, that.id) && Objects.equals(borrowDate, that.borrowDate) && Objects.equals(returnDate, that.returnDate) && Objects.equals(book, that.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
