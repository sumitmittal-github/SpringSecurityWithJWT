package com.sumit.controller;

import com.sumit.entity.User;
import com.sumit.security.JwtUtils;
import com.sumit.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Log4j2
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        User registeredUser = userService.registerUser(user);
        if(registeredUser != null)
            return new ResponseEntity<>("User registered successfully.", HttpStatus.CREATED);
        else
            return new ResponseEntity<>("Registration failed.", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try{
            // this will call overloaded loadUserByUsername to validate the user
            // if user found in DB then it will use PasswordEncoded to match the password
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            // if user is authenticated in above line, only then this line will be called
            String jwtToken = jwtUtils.generateToken(user.getUsername());
            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
        } catch(Exception e){
            log.error("Exception occurred while login ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }



}