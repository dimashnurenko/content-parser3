package com.huk.web;

import com.huk.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto createUser(@RequestBody CreateUserDto userDto) {
        return userService.saveUser(userDto);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured(value = "SUPER_ADMIN")
    public List<UserDto> getAllUsers() {
        return Collections.emptyList();
    }
}

