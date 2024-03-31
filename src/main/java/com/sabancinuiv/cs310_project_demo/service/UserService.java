package com.sabancinuiv.cs310_project_demo.service;
import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.model.UserRegistrationDTO;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import com.sabancinuiv.cs310_project_demo.repository.TodoEntryRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    private static final boolean DEBUG = true;

    // Field Injection
    @Autowired private UserRepository userRepo;
//  @Autowired private TodoEntryRepository todoRepo;

    // Password hashing için bunu kullandık, bcrypt kullanıyor.
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    void init(){
        logger.info("(DEBUG)(UserService.java) UserService has been initialized!");
    }

    public String registerUser(UserRegistrationDTO dto) {

       String dtoValidatorResult = UserService.DtoValidator(dto);

       if (dtoValidatorResult.equals("SUCCESS")){

           // BUNU COMMENT OUT ETTIM, DB DE VAR MI YOK MU CHECKI ÇALIŞMIYOR !
//           List<User> existingUserOpt = userRepo.findUserByUsername(dto.getUsername());
//           if (existingUserOpt != null) {
//               return "USER REGISTRATION CANNOT BE COMPLETED DUE TO USERzNAME ALREADY EXISTS";
//           }

           // Fields of this object will be filled than saved to the DB
           User newUser = new User();

           // mapping dto username to User username
           newUser.setUsername(dto.getUsername());

           // mapping hashed dto password to User hashedPassword
           String hash = passwordEncoder.encode(dto.getPassword());
           newUser.setPasswordHash(hash);

           // mapping dto username to User username
           newUser.setEmail(dto.getEmail());

           // mapping dto username to User username
           newUser.setPhone(dto.getPhone());

           // IP generation & mapping
           String ip = "139.108.14.66"; //Sonra FakeGen implemente ederiz
           newUser.setRegisterIp(ip);

           // Timestamp generation & mapping
           LocalDateTime temp = LocalDateTime.now();
           newUser.setRegisterDate(temp);
           newUser.setLastLoginDate(temp);

           String secret = generateSecret(dto.getUsername(), newUser.getPasswordHash());
           newUser.setSecret(secret);

           userRepo.save(newUser);

           logger.info("(DEBUG)(UserService.java) User Saved: " + newUser.toString());

           return "SUCCESS";

       } else {
           return "USER REGISTRATION CANNOT BE COMPLETED DUE TO " + dtoValidatorResult + "\n";
       }
    }

    // Function to input-check UserRegisterationDTO's
    public static String DtoValidator(UserRegistrationDTO dto){

        if (dto.getUsername() == null || dto.getUsername().isEmpty()) {
            return "USERNAME CANNOT BE EMPTY";
        }
        if (dto.getPassword() == null || dto.getPassword().length() <= 6) {
            return "PASSWORD MUST BE AT LEAST 6 CHARACTERS";
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

    /*//TODO, tam denemedim kullanıcıdan POST almadıgımız için
    //Check https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html

    public String authenticateUser(String username, String password){

        User user = userRepo.findByUsername(username);

        if (user == null) {
            if (DEBUG) {
                System.out.print("(DEBUG) USER \"" + user.getUsername() + " \" DEEMED TO BE NULL\n");
            }
            return "AUTH_FAILED";

        }
        if (passwordEncoder.matches(user.getPasswordHash(), passwordEncoder.encode(password))) {

        } else {
            return "AUTH_FAILED";
        }
    }*/

    private String generateSecret(String username, String passwordHash) {
        StringBuilder sb = new StringBuilder();

        sb.append(username.toLowerCase());
        sb.append(passwordHash.toUpperCase());

        String result = sb.toString();

        // Convert the string to a Character array, not char[].
        Character[] resultArr = result.chars().mapToObj(c -> (char) c).toArray(Character[]::new);

        // Now you can correctly create a List<Character> and shuffle it.
        List<Character> resultList = Arrays.asList(resultArr);
        System.out.print("Contes of resultString is: (BEFORE SHUFFLE) " + resultList + "\n");
        Collections.shuffle(resultList);

        // To print or return the shuffled characters, you can join them back into a string. (streams öğrenmek lazım amk)
        String shuffledString = resultList.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());


        System.out.print("Contents of resultList is: (AFTER SHUFFLE) " + shuffledString);

        return shuffledString;
    }






}
