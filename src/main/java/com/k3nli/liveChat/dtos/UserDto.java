package com.k3nli.liveChat.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserDto {

    String id;
    
    String username;
    
    String password;

    String role;

}
