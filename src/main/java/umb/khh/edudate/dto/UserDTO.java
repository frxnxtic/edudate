package umb.khh.edudate.dto;

import lombok.*;

import java.util.Date;

@Builder
@Data
public class UserDTO {
    private String username;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String faculty;
    private String profileDescription;
    private String[] interest;

}
