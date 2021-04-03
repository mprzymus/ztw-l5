package pl.mprzymus.ztwl5.books.model;

import lombok.*;
import pl.mprzymus.ztwl5.authors.model.Author;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tittle;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private int pages;

}
