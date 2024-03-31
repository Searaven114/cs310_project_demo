package com.sabancinuiv.cs310_project_demo.service;


import com.sabancinuiv.cs310_project_demo.model.TodoEntry;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoEntryService {

    Logger logger = LoggerFactory.getLogger(TodoEntryService.class);

    @Autowired
    TodoEntryRepository todoRepo;

    @PostConstruct
    void init(){
        logger.info("(DEBUG)(TodoEntryService.java) TodoEntryService has been initialized!");
    }


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
