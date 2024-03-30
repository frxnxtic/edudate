package umb.khh.edudate.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserDTO
{
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String email;
    private String passwordHash;
    private String faculty;
    private String profileDescription;
    private String[] interest;

    public UserDTO(String name, String surname, LocalDate dateOfBirth, String email, String passwordHash, String faculty, String profileDescription, String[] interest) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.passwordHash = passwordHash;
        this.faculty = faculty;
        this.profileDescription = profileDescription;
        this.interest = interest;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setProfileDescription(String profileDescription) {
        this.profileDescription = profileDescription;
    }

    public void setInterest(String[] interest) {
        this.interest = interest;
    }
}
