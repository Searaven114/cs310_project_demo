package com.sabancinuiv.cs310_project_demo.repository;

import com.sabancinuiv.cs310_project_demo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {


}
