package com.k3nli.liveChat.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.k3nli.liveChat.dtos.ChatMessageDto;
import com.k3nli.liveChat.service.MessageService;

@Controller
public class ChatController {

    @Autowired
    MessageService messageService;

    @Autowired
    SimpMessagingTemplate messagingTemplate;    

    @MessageMapping("/chat/{roomId}/sendMessage")
    @SendTo("/topic/chat/{roomId}")
    public ChatMessageDto getMessage(@DestinationVariable String roomId, @Payload ChatMessageDto message) {
        LocalDateTime time = LocalDateTime.now();
        message.setTimestamp(time);
        messageService.persistChatMessages(roomId, message);
        return message;
    }

}
