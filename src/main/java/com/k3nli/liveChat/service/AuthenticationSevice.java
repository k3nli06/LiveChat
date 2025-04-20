package com.k3nli.liveChat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.k3nli.liveChat.dtos.*;
import com.k3nli.liveChat.persistence.entity.UserEntity;
import com.k3nli.liveChat.persistence.repository.UserRepository;

@Service
public class AuthenticationSevice {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository repository;
    @Autowired
    JwtService jwtService;
    @Autowired
    PasswordEncoder passwordEncoder;

    public AuthenticationResponseDto login(UserDto userDto) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        UserEntity user = repository.findUserByName(userDto.getUsername()).get();

        String token = jwtService.getToken(user);
        return new AuthenticationResponseDto(token);
    }

    public AuthenticationResponseDto register(UserDto user) {
        UserEntity register = UserEntity.builder()
                .name(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .build();

        UserEntity registered = repository.save(register);
        String token = jwtService.getToken(registered);
        return new AuthenticationResponseDto(token);
    }

}
