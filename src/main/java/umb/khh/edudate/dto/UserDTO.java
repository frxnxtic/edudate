package umb.khh.edudate.dto;

import lombok.*;

import java.util.Date;

@Builder
@Data
public class UserDTO {
    private String username;
    private String token;
    private String name;
    private String surname;
    private short likes;
    private int dislikes;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String faculty;
    private String profileDescription;
    private String[] interest;

}
