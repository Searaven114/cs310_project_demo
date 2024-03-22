package com.sabancinuiv.cs310_project_demo.repository;

import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoEntryRepository extends MongoRepository<TodoEntry, String> {

}