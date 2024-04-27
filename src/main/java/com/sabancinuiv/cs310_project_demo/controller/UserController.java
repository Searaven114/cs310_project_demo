package com.sabancinuiv.cs310_project_demo.controller;

import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.service.UserRegistrationDTO;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import com.sabancinuiv.cs310_project_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;


    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO dto) {

        String reply = userService.registerUser(dto);

        if (reply.equals("SUCCESS")) {
            return new ResponseEntity<>("USER HAS BEEN REGISTERED", HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(reply);
        }
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/user/admin/show-all-users")
    public ResponseEntity<?> getUsers(){
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok().body(users);
    }

    @GetMapping("/deneme123")
    public String  RespondToLogin(){
        return "LOGIN RESPONSE";
    }


}

