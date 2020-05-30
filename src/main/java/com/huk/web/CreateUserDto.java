package com.huk.web;

public class CreateUserDto extends UserDto {

    private  String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
