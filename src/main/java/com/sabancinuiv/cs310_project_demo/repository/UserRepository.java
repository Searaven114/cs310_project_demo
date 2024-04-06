package com.sabancinuiv.cs310_project_demo.repository;

import com.sabancinuiv.cs310_project_demo.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmail();

    @Override
    <S extends User> List<S> findAll(Example<S> example);


    Optional<User> findByUsername(String username);

    List<User> findUserByUsername(String username);


}
