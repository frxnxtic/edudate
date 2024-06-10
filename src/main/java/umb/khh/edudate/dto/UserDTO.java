package umb.khh.edudate.dto;

import lombok.*;
import umb.khh.edudate.entity.Interest;

import java.util.Date;
import java.util.Set;

@Builder
@Data
public class UserDTO {
    private String username;
    private String token;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String faculty;
    private String profileDescription;
    private short likes;
    private int dislikes;
    private Set<Interest> interests;

}
