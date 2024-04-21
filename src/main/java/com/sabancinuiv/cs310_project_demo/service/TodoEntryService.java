package com.sabancinuiv.cs310_project_demo.service;

import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

//TODO -> TodoEntryController'den logic'i buraya taşımak lazım. Controllerin görevi veriyi işlemek değil, veriyi Service layer'e
//          aktarmaktır normalde.
@Service
public class TodoEntryService {

    @Autowired
    TodoEntryRepository todoRepo;

    @Autowired
    UserRepository userRepo;

    //Validator Bulunsun diye yaptım, detaylandırılması lazım adam gibi, title boş olamaz vs vs
    public static String validate (TodoEntryDTO dto){
        if (dto.getTitle() != null){
            return "PASSED";
        } else {
            return "TITLE CANNOT BE EMPTY";
        }
    }




}
