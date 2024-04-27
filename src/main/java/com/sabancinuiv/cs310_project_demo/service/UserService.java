package com.sabancinuiv.cs310_project_demo.service;
import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final boolean DEBUG = true;

    // Field Injection
    @Autowired private UserRepository userRepo;

    @Autowired private TodoEntryRepository todoRepo;

    @Autowired private PasswordEncoder encoder;


    public String registerUser(UserRegistrationDTO dto) {

       String dtoValidatorResult = UserService.DtoValidator(dto);

       if (dtoValidatorResult.equals("SUCCESS")){

           User check = userRepo.findByUsername( dto.getUsername() );

           if (check != null){
               return "USER REGISTRATION CANNOT BE COMPLETED DUE TO USERNAME ALREADY EXISTS";
           }

           // Mapping the new user entry
           User newUser = new User();

           newUser.setUsername(dto.getUsername());

           newUser.setPasswordHash( encoder.encode( dto.getPassword() ) );

           newUser.setEmail(dto.getEmail());

           newUser.setPhone(dto.getPhone());

           List<String> roles = new ArrayList<>();
           roles.add("ROLE_USER");
           newUser.setRoles(roles);

           // IP generation & mapping
           String ip = "139.108.14.66"; //Sonra FakeGen implemente ederiz
           newUser.setRegisterIp(ip);

           // Timestamp generation & mapping
           LocalDateTime temp = LocalDateTime.now();
           newUser.setRegisterDate(temp);

           // logic endpointi spring security default configuration tarafından saglandıgı icin buna mudahale edemiyorum diger endpointler gibi
           // normalde her loginde, login oldugu ip addressi nin guncellenmesi lazım.
           newUser.setLastLoginDate(temp);

           userRepo.save(newUser);

           logger.info("(DEBUG)(UserService.java) USER HAS BEEN SAVED : " + newUser.toString());

           return "SUCCESS";

       } else {
           return "USER REGISTRATION CANNOT BE COMPLETED DUE TO " + dtoValidatorResult;
       }
    }

    // Function to input-check UserRegisterationDTO's
    //TODO BURAYA REGEX CHECKER IMPLEMENTE ET EMAIL VE TEL NO ICIN
    public static String DtoValidator(UserRegistrationDTO dto){

        if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
            return "USERNAME CANNOT BE EMPTY";
        }
        if (dto.getPassword() == null || dto.getPassword().length() <= 3) {
            return "PASSWORD MUST BE AT LEAST 3 CHARACTERS";
        }
        if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
            return "EMAIL CANNOT BE EMPTY";
        }
        //TODO Phone check'i detaylandır ileride
        if (dto.getPhone() == null || dto.getPhone().isEmpty()){
            return "PHONE CANNOT BE EMPTY";
        }

        return "SUCCESS";
    }

}
