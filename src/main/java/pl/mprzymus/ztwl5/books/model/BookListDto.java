package pl.mprzymus.ztwl5.books.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) //annotation required for records
public record BookListDto(List<Book> books){}
