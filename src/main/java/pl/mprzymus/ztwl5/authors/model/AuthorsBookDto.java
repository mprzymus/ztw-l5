package pl.mprzymus.ztwl5.authors.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record AuthorsBookDto(Integer id, String tittle, int pages) {
}
