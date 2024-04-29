package com.sabancinuiv.cs310_project_demo.controller;

import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import com.sabancinuiv.cs310_project_demo.dto.UserRegistrationDTO;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import com.sabancinuiv.cs310_project_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepo;

    @Autowired
    TodoEntryRepository todoRepo;


    /**
     * Handles user registeration by utilizing service layer.
     * @param dto The object to be taken as an input for the registeration, includes username, password etc etc.
     * @return a ResponseEntity that either carries the result state of the process as a String, Exception is incorporated into this string.
     */
    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDTO dto) {

        String reply = userService.registerUser(dto);

        if (reply.equals("SUCCESS")) {
            return new ResponseEntity<>("USER HAS BEEN REGISTERED", HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().body(reply);
        }
    }

    //Admin tool
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/user/admin/show-all-users")
    public ResponseEntity<?> getUsers(){
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok().body(users);
    }

    /**
     * A method that will return information about the user and his/her entries, will be used in "My profile" part of frontend
     * @return a ResponseEntity with the information of the user.
     */
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("user/profile")
    public ResponseEntity<?> showProfile(){

        //Association logic
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userRepo.findByUsername(username);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("USER NOT FOUND");
        }

            long entryCount = todoRepo.countByUserId(user.getUserId());
            long completedCount = todoRepo.countByUserIdAndStatus(user.getUserId(), true);

            // Manually constructing the JSON response on-fly, didn't want to bother with new dtos
            Map<String, Object> profile = new HashMap<>();

            profile.put("userId", user.getUserId());
            profile.put("username", user.getUsername());
            profile.put("email", user.getEmail());
            profile.put("phone", user.getPhone());
            profile.put("registerDate", user.getRegisterDate());
            profile.put("lastLoginDate", user.getLastLoginDate());
            profile.put("entryCount", entryCount);
            profile.put("completedCount", completedCount);

            return ResponseEntity.ok().body(profile);

    }



}

