package umb.khh.edudate.dto;

import lombok.Getter;


public record SignupDTO(String username, String name, String surname, String dateOfBirth, String email, String password, String faculty, String profileDescription, String[] interest) {

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getProfileDescription() {
        return profileDescription;
    }

    public String[] getInterest() {
        return interest;
    }
}
