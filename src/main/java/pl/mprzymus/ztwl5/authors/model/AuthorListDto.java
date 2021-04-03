package pl.mprzymus.ztwl5.authors.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record AuthorListDto(List<AuthorDto> authors) {
}
