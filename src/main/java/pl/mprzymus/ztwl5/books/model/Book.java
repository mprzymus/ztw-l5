package pl.mprzymus.ztwl5.books.model;

import lombok.*;
import pl.mprzymus.ztwl5.authors.model.Author;
import pl.mprzymus.ztwl5.book_borrow.BookBorrow;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tittle;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private Set<BookBorrow> borrowHistory = new HashSet<>();

    private int pages;
}
