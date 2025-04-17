package com.k3nli.liveChat.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.k3nli.liveChat.dtos.ChatMessageDto;
import com.k3nli.liveChat.service.MessageService;

@RestController
public class ChatMessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("chat/{roomId}/history")
    public List<ChatMessageDto> getMethodName(@PathVariable(value = "roomId") String roomId, Principal principal) {
        return messageService.getHistoryMessages(roomId);
    }
}
