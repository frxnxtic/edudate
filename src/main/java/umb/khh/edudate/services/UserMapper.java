package umb.khh.edudate.services;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import umb.khh.edudate.dto.SignupDTO;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "token", target = "token")
    static UserDTO toUserDTO(User user) {
        return null;
    }

}