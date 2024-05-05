package umb.khh.edudate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;
    private String passwordHash;
    private String faculty;
    private String profileDescription;
    private String[] interest;

}
