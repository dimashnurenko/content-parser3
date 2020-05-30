package com.huk.web;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDto {

    private Long id;
    //@JsonProperty("email")
    private String email;
   //@JsonProperty("login")
    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
