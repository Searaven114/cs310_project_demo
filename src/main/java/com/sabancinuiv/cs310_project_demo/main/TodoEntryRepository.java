package com.sabancinuiv.cs310_project_demo.main;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoEntryRepository extends MongoRepository<TodoEntry, String> {

    List<TodoEntry> findByUserId(String UserId);

    Optional<TodoEntry> findById(String id);



}