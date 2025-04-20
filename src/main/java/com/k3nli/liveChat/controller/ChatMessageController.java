package com.k3nli.liveChat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.k3nli.liveChat.dtos.ChatMessageDto;
import com.k3nli.liveChat.service.MessageService;

@RestController
@RequestMapping("/chat")
public class ChatMessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/{roomId}/history")
    public ResponseEntity<List<ChatMessageDto>> getMethodName(@PathVariable(value = "roomId") String roomId) {
        return ResponseEntity.ok(messageService.getHistoryMessages(roomId));
    }
}
