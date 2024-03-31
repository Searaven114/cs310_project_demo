package com.sabancinuiv.cs310_project_demo.controller;

import com.sabancinuiv.cs310_project_demo.model.UserRegistrationDTO;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import com.sabancinuiv.cs310_project_demo.service.UserService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostConstruct
    void init(){
        logger.info("(DEBUG)(UserController.java) UserController has been initialized!");
    }

    //TODO
    //  @PostMapping("/register") YAPILDI
    //  @PostMapping("/login")
    //  @GetMapping("/account")
    //  @GetMapping("/account/settings

    @PostMapping("/user/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO dto) {

        // logic i "service" layere kaydırdım, todoentry de de bu yapılmalı
        String reply = userService.registerUser(dto);

        if (reply.equals("SUCCESS")) {
            return ResponseEntity.ok().body("User registered successfully");
        } else {
            return ResponseEntity.badRequest().body(reply);
        }
    }

    //Debug için bu, normalde böyle bir listeleme olmayacak submit versiyonda
    @GetMapping("/user")
    public List<?> getUsers(){
        List users = userRepo.findAll();
        return users;
    }


}

