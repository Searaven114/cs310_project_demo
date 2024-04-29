package com.sabancinuiv.cs310_project_demo.service;

import com.sabancinuiv.cs310_project_demo.model.User;
import com.sabancinuiv.cs310_project_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    /**
     * User information will be loaded from the database in an object form for Auth processing.
     * @param username the username identifying the user to be loaded
     * @return a UserDetails object containing the user's information
     * @throws UsernameNotFoundException if the user cannot be found in the database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("USER " + username + " WAS NOT FOUND");
        } else {
            return user;
        }
    }
    /* TODO
    *   UsernameNotFoundException'u işlemeli ve değerlendirmeliyiz ileride...
    *
    * */



}