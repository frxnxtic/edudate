package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import umb.khh.edudate.dto.LoginDTO;
import umb.khh.edudate.dto.SignupDTO;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.security.AuthProvider;
import umb.khh.edudate.services.UserServices;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8080"})
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserServices service;

    @Autowired
    private AuthProvider auth;


    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        UserDTO user = service.login(loginDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignupDTO userDTO) {
        UserDTO user = service.register(userDTO);
        return ResponseEntity.ok(user);
    }
}
