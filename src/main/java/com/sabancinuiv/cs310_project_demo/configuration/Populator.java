package com.sabancinuiv.cs310_project_demo.configuration;
import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class Populator {

    @Autowired
    private TodoEntryRepository todoRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    PasswordEncoder encoder;

    @PostConstruct
    public void init(){

        userRepo.deleteAll();

        List<User> users = Arrays.asList(
                new User("fuat",  encoder.encode("avni"), "fuatavni@gmail.com", "05331627612"),
                new User("user1", encoder.encode("pw1"), "tsanders@example.net", "05321790021"),
                new User("user2", encoder.encode("pw2"), "millertiffany@example.com", "05996236571"),
                new User("user3", encoder.encode("pw3"), "nathan75@example.org", "05337617781"),
                //yeni ekleyeceksen buradan ekle:

                new User("admin", encoder.encode("admin123"), "admin@gov.tr", "05333133131")
        );

        for (User user : users) {
            List<String> roles = new ArrayList<>();
            if ("admin".equals(user.getUsername())) {
                roles.add("ROLE_ADMIN");
                roles.add("ROLE_USER");
            } else {
                roles.add("ROLE_USER");
            }
            user.setRoles(roles);
        }

        userRepo.saveAll(users);

        List<User> userList = userRepo.findAll();

        //Status false = checked
        //Status true= unchecked
        List<TodoEntry> entries = Arrays.asList(
                new TodoEntry(null, userList.get(0).getUserId(), "11111111111111111", "1", "Work1", true, LocalDateTime.now(), LocalDateTime.now().plusDays(1)),
                new TodoEntry(null, userList.get(0).getUserId(), "22222222222222222", "2", "Work2", true, LocalDateTime.now(), LocalDateTime.now().plusDays(2)),
                new TodoEntry(null, userList.get(0).getUserId(), "33333333333333333", "3", "Work3", true, LocalDateTime.now(), LocalDateTime.now().plusDays(3)),
                new TodoEntry(null, userList.get(0).getUserId(), "Read a book about Spring Boot", "Personal Development", "Education", false, LocalDateTime.now(), LocalDateTime.now().plusWeeks(2)),
                new TodoEntry(null, userList.get(0).getUserId(), "6 ay once bıttı aq", "Personal", "ffff", false, LocalDateTime.now(), LocalDateTime.now().minusYears(10)),
                new TodoEntry(null, userList.get(0).getUserId(), "9 ay once bıttı aq", "Personal", "ffff", false, LocalDateTime.now(), LocalDateTime.now().minusYears(3)),
                new TodoEntry(null, userList.get(0).getUserId(), "12 ay once bıttı aq", "Personal", "ffff", false, LocalDateTime.now(), LocalDateTime.now().minusYears(6)),

                new TodoEntry(null, userList.get(1).getUserId(), "Plan the weekly meeting agenda", "Meeting", "Work", true, LocalDateTime.now(), LocalDateTime.now().plusHours(48)),
                new TodoEntry(null, userList.get(2).getUserId(), "DENEME", "Meeting", "Work", true, LocalDateTime.now(), LocalDateTime.now().plusHours(48)),
                new TodoEntry(null, userList.get(3).getUserId(), "LOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLO", "LO-LO", "lolo", true, LocalDateTime.now(), LocalDateTime.now().plusHours(48)),
                new TodoEntry(null, userList.get(0).getUserId(), "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx", "x", "x", false, LocalDateTime.now(), LocalDateTime.now().plusWeeks(2))


        );

        todoRepo.deleteAll();

        todoRepo.saveAll(entries);

    }


}

