package umb.khh.edudate.dto;

import lombok.Getter;

@Getter
public record SignupDTO(String username, String name, String surname, String dateOfBirth, String email, String password, String faculty, String profileDescription, String[] interest) {

}
