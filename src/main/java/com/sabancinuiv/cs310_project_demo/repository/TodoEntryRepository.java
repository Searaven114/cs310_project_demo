package com.sabancinuiv.cs310_project_demo.repository;

import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TodoEntryRepository extends MongoRepository<TodoEntry, String> {

    List<TodoEntry> findByUserId (String userId);

    void deleteAllByUserId(String userId);

    TodoEntry findByIdAndUserId(String userId, String id);

    List<TodoEntry> findByUserIdAndStatus(String userId, Boolean status);

}