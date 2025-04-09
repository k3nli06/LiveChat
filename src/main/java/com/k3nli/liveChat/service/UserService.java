package com.k3nli.liveChat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k3nli.liveChat.dtos.UserDto;
import com.k3nli.liveChat.persistence.entity.User;
import com.k3nli.liveChat.persistence.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<UserDto> getAllUsers() {
        List<User> users = repository.findAll();

        return users.stream().map(
                user -> new UserDto(user.getId(), user.getName(), user.getPassword(), user.getRoles())).toList();
    }

    public UserDto getUserById(String id) {
        User user = repository.findById(id).get();

        return new UserDto(user.getId(), user.getName(), user.getPassword(), user.getRoles());
    }

    public UserDto createUser(UserDto user) {
        User saved = repository.save(User.builder()
                .name(user.getName())
                .password(user.getPassword())
                .roles("ROLE")
                .build());

        return UserDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .password(saved.getPassword())
                .role(saved.getRoles())
                .build();
    }

    public void updateUser(String id, UserDto userDto) {
        User user = repository.findById(id).orElse(null);

        if(user != null) {
            user.setName(userDto.getName());
            user.setPassword(userDto.getPassword());
            repository.save(user);
        }

    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }

}
