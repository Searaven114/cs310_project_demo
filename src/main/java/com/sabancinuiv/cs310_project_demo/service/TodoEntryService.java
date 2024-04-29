package com.sabancinuiv.cs310_project_demo.service;

import com.sabancinuiv.cs310_project_demo.dto.TodoEntryDTO;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoEntryService {

    @Autowired
    TodoEntryRepository todoRepo;

    @Autowired
    UserRepository userRepo;

    //TODO
    //  will be completed later, will include some regex mechanisms
    public static String validate (TodoEntryDTO dto){
        if (dto.getTitle() != null){
            return "PASSED";
        } else {
            return "TITLE CANNOT BE EMPTY";
        }
    }




}
