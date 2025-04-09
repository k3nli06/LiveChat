package com.k3nli.liveChat.persistence.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import com.k3nli.liveChat.dtos.ChatMessageDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("mensajes")
public class ChatMessages {
    String roomId;
    
    ChatMessageDto messages;
}
