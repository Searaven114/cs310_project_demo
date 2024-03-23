package com.sabancinuiv.cs310_project_demo.main;

import com.sabancinuiv.cs310_project_demo.main.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    User findByUserId(String id);

    User findByUsername(String username);

    //User findBy

}
