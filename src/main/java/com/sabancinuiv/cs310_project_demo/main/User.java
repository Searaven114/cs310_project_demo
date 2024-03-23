//⚠✅✻✔❂➾➜➢☞۞☢✪✶⁂܀

//https://www.youtube.com/watch?v=L9oWG6aj_U8

package com.sabancinuiv.cs310_project_demo.main;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;

@Document(collection = "users")
public class User {

    @Id
    private String UserId;
    private String username;
    private String passwordHash; //Hashing and DeHashing will be done in, will be utilized after skeleton of the project is written
    private String email;
    private String phone;
    private LocalDateTime registerDate; //Will kept just as a record
    private LocalDateTime lastLoginDate; //Will kept just as a record
    private String registerIp; //Will kept just as a record, will be generated randomly in logic process


    public User(){}

    public User(String userId, String username, String passwordHash, String email, String phone, LocalDateTime registerDate, LocalDateTime lastLoginDate, String registerIp) {
        UserId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phone = phone;
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.registerIp = registerIp;
    }


    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Implement timestamping in the backend(service part), not here or in DB
        public LocalDateTime getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(LocalDateTime registerDate) {
            this.registerDate = registerDate;
        }

        public LocalDateTime getLastLoginDate() {
            return lastLoginDate;
        }

        public void setLastLoginDate(LocalDateTime lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId='" + UserId + '\'' +
                ", username='" + username + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", registerDate=" + registerDate +
                ", lastLoginDate=" + lastLoginDate +
                ", registerIp='" + registerIp + '\'' +
                '}';
    }


}
