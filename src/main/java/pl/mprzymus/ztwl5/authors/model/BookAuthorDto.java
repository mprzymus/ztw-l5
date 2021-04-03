package pl.mprzymus.ztwl5.authors.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY) //annotation required for records
public record BookAuthorDto(Integer id, String firstName, String lastName) {
}
