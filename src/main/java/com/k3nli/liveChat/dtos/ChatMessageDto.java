package com.k3nli.liveChat.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ChatMessageDto {
    LocalDateTime timestamp;
    String sender;
    String content;
    String type;
}
