package com.sabancinuiv.cs310_project_demo.model;

import org.springframework.stereotype.Component;

// User register sürecinde direkt user objesi yerine bu aracı obje kullanılıyor, bunun değerlerini daha sonra
// yaratacağımız objeye maplıyoruz.

@Component
public class UserRegistrationDTO {

    private String username;
    private String password; // This will be the plaintext password from the user
    private String email;
    private String phone;


    public UserRegistrationDTO(){}

    public UserRegistrationDTO(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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


}

/* Niye veri alışverişi direkt "User" objeleri ile değil de özel DTO'lar ile dönmeli ?
   Cevap:

Security: Your User entity might contain fields that should never be exposed to users or that users should not be able to set during registration,
    like roles, permissions, or account status fields. A DTO lets you expose only the fields necessary for registration (e.g., username, email, password).

Simplified Validation: DTOs allow for tailored validation rules that fit the specific use case. For example, during registration,
    you might want to ensure the password field meets certain strength criteria and that the email is in a valid format, which can be directly annotated in the DTO.

Decoupling: Using DTOs helps decouple your database model from your API's public interface.
    This abstraction layer means changes to your database model (like adding a new field to the User entity) don't necessarily impact your API contract.

Customization for the Use Case: Sometimes, the information needed for registration differs from what's stored in the User entity.
    A DTO can accommodate additional fields like a captcha response or terms and conditions acceptance without altering the User entity.
 */
