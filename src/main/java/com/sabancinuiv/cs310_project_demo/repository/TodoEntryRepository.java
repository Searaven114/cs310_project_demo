package com.sabancinuiv.cs310_project_demo.repository;

import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoEntryRepository extends MongoRepository<TodoEntry, String> {

    // Find TodoEntry by user ID
    List<TodoEntry> findByUserId(String userId);

    // Find TodoEntries with a certain status
    List<TodoEntry> findByStatus(boolean status);

    // Delete TodoEntry with given id

}