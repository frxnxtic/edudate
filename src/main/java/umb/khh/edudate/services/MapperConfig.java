package umb.khh.edudate.services;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import umb.khh.edudate.dto.LoginDTO;
import umb.khh.edudate.dto.SignupDTO;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.entity.User;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        modelMapper.getConfiguration().setFieldMatchingEnabled(true);

        modelMapper.createTypeMap(User.class, UserDTO.class);
        modelMapper.createTypeMap(User.class, LoginDTO.class);
        modelMapper.createTypeMap(User.class, SignupDTO.class);

        return modelMapper;
    }
}