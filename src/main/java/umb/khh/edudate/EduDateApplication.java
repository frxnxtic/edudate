package umb.khh.edudate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import umb.khh.edudate.entity.User;
import umb.khh.edudate.services.UserServices;

import java.util.Date;
import java.util.Calendar;

@SpringBootApplication
public class EduDateApplication{

    @Autowired
    private UserServices userServices;

    public static void main(String[] args) {
        SpringApplication.run(EduDateApplication.class, args);
    }


}
