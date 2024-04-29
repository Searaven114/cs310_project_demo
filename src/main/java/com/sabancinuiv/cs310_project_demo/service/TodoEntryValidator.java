package com.sabancinuiv.cs310_project_demo.service;

import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoEntryValidator {

    @Autowired
    private final TodoEntryRepository todoRepo;

    @Autowired
    public TodoEntryValidator(TodoEntryRepository todoRepo) {
        this.todoRepo = todoRepo;
    }

    /**
     * Simple input checker, will be extended later on with regex checkers.
     * @param entry The entry to be processed.
     * @return A string representing the state of the result of the process, exception handling is integrated in this string
     */
    public String validate(TodoEntry entry) {
        if (entry.getUserId() == null){
            return "USER ID CANNOT BE EMPTY";
        } else if ( entry.getTitle() == null){
            return "TITLE CANNOT BE EMPTY";
        } else if ( entry.getContent() == null){
            return "CONTENT CANNOT BE EMPTY";
        }

        return "PASSED";
    }
}
