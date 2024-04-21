package com.sabancinuiv.cs310_project_demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "users")
public class User implements UserDetails {

    @Id
    private String UserId;
    private String username;
    private String passwordHash;
    private String email;
    private String phone;
    private List<String> roles; // Store roles as a list of strings
    private LocalDateTime registerDate;
    private LocalDateTime lastLoginDate;
    private String registerIp; //Will kept just as a record, will be generated randomly in logic process

    public User(){}

    public User(String userId, String username, String passwordHash, String email, String phone, List<String> roles, LocalDateTime registerDate, LocalDateTime lastLoginDate, String registerIp) {
        UserId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.registerDate = registerDate;
        this.lastLoginDate = lastLoginDate;
        this.registerIp = registerIp;
    }

    public User(String username, String passwordHash, String email, String phone) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.phone = phone;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getUserId() {
        return UserId;
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
