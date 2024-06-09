package umb.khh.edudate.dto;

import umb.khh.edudate.entity.enums.Interest;

import java.util.Date;
import java.util.Set;

public record SignupDTO(String username, String name, String surname, Date dateOfBirth, String email, String password, String faculty, String profileDescription, Set<Interest> interests) {

}
