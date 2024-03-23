package com.sabancinuiv.cs310_project_demo.main;
import com.sabancinuiv.cs310_project_demo.main.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.sabancinuiv.cs310_project_demo.main.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final boolean DEBUG = true;

    private final UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder;


    //Constructor DI
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    //TODO, tam denemedim kullanıcıdan POST almadıgımız için
    //Check https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html
    public boolean authenticateUser(String username, String password){

        User user = userRepository.findByUsername(username);

        if (user == null) {
            if (DEBUG) {
                System.out.print("(DEBUG) USER \"" + user.getUsername() + " \" DEEMED TO BE NULL\n");
            }
            return false;

        }
        if (passwordEncoder.matches(user.getPasswordHash(), passwordEncoder.encode(password))) {
            return true;
        } else {
            return false;
        }
    }





}
