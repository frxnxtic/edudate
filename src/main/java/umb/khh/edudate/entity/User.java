package umb.khh.edudate.entity;

import javax.persistence.*;
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
    private String userName;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;
    private String passwordHash;
    private String faculty;
    private String profileDescription;
    private String[] interest;

}
