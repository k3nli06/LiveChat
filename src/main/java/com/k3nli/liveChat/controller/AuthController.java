package com.k3nli.liveChat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k3nli.liveChat.dtos.AuthenticationResponseDto;
import com.k3nli.liveChat.dtos.UserDto;
import com.k3nli.liveChat.service.AuthenticationSevice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    AuthenticationSevice authenticationSevice;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody UserDto user) {
        AuthenticationResponseDto token = authenticationSevice.login(user);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> register(@RequestBody UserDto user) {
        AuthenticationResponseDto token = authenticationSevice.register(user);
        return ResponseEntity.ok(token);
    }
    
}
