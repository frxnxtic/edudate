package umb.khh.edudate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umb.khh.edudate.dto.LoginDTO;
import umb.khh.edudate.dto.UserDTO;
import umb.khh.edudate.security.AuthProvider;
import umb.khh.edudate.services.UserServices;

@RestController
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
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
        UserDTO user = service.signup(userDTO);
        return ResponseEntity.ok(user);
    }
}
