package com.k3nli.liveChat.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k3nli.liveChat.dtos.UserDto;
import com.k3nli.liveChat.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping()
    public List<UserDto> getAllusers() {
        return service.getAllUsers();
    }

    @GetMapping("/me")
    public Map<String, String> getLoggedUser(Principal principal) {
        return Map.of("username", principal != null ? principal.getName() : null);
    }

    @GetMapping("/{id}")
    public UserDto getMethodName(@PathVariable(value = "id") String id) {
        return service.getUserById(id);
    }
    
    @PutMapping("/{id}")
    public void updateUser(@PathVariable(value = "id") String id, @RequestBody UserDto userDto) {
        service.updateUser(id, userDto);
    }

    @PostMapping
    public UserDto addUser(@RequestBody UserDto user) {
        return service.createUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable(name = "id") String id) {
        service.deleteUser(id);
    }

}
