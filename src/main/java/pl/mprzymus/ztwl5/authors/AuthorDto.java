package pl.mprzymus.ztwl5.authors;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record AuthorDto(Integer id, String firstName, String lastName, List<AuthorsBookDto> books) {
}
