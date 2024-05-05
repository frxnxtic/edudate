package umb.khh.edudate.entity;

import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;

import java.time.LocalDate;

public class User {
    @Id @GeneratedValue
    private Long id;
    @Getter
    private String name;
    @Getter
    private String surname;
    @Getter
    private LocalDate dateOfBirth;
    @Getter
    private String email;
    @Getter
    private String passwordHash;
    @Getter
    private String faculty;
    @Getter
    private String profileDescription;
    @Getter
    private String[] interest;

    public void setId(Long id) {
        this.id = id;
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

    public void getFaculty(String faculty) {
    }
}
