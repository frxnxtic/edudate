package umb.khh.edudate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String passwordHash;
    @Column(name = "email")
    private String email;
    @Column(name = "dob")
    private Date dateOfBirth;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "city")
    private String city;
    @Column(name = "likes")
    private short like;
    @Column(name = "faculta")
    private String faculty;
    @Column(name = "description")
    private String profileDescription;
    @Column(name = "social_links")
    private String social_links;
//    @Column(name = "username")
//    private String[] interest;
}
