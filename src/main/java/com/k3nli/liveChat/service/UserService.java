package com.k3nli.liveChat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k3nli.liveChat.dtos.UserDto;
import com.k3nli.liveChat.persistence.entity.UserEntity;
import com.k3nli.liveChat.persistence.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<UserDto> getAllUsers() {
        List<UserEntity> users = repository.findAll();

        return users.stream().map(
                user -> new UserDto(user.getId(), user.getName(), user.getPassword(), user.getRoles())).toList();
    }

    public UserDto getUserById(String id) {
        UserEntity user = repository.findById(id).get();

        return new UserDto(user.getId(), user.getName(), user.getPassword(), user.getRoles());
    }

    public UserDto createUser(UserDto user) {
        UserEntity saved = repository.save(UserEntity.builder()
                .name(user.getUsername())
                .password(user.getPassword())
                .roles("ROLE")
                .build());

        return UserDto.builder()
                .id(saved.getId())
                .username(saved.getName())
                .password(saved.getPassword())
                .role(saved.getRoles())
                .build();
    }

    public void updateUser(String id, UserDto userDto) {
        UserEntity user = repository.findById(id).orElse(null);

        if(user != null) {
            user.setName(userDto.getUsername());
            user.setPassword(userDto.getPassword());
            repository.save(user);
        }

    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }

}
