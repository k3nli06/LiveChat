package com.k3nli.liveChat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k3nli.liveChat.dtos.ChatMessageDto;
import com.k3nli.liveChat.persistence.entity.ChatMessages;
import com.k3nli.liveChat.persistence.repository.ChatRepository;

@Service
public class MessageService {

    @Autowired
    ChatRepository repository;

    public List<ChatMessageDto> getHistoryMessages(String roomId) {
        List<ChatMessages> messages = repository.findHistoryMessages(roomId);

        List<ChatMessageDto> messagesDto = messages.stream().map(
                mes -> new ChatMessageDto(mes.getMessages().getTimestamp(),
                        mes.getMessages().getSender(),
                        mes.getMessages().getContent(),
                        mes.getMessages().getType()))
                .toList();
                return messagesDto;
    }

    public void persistChatMessages(String roomId, ChatMessageDto messages) {
        repository.save(ChatMessages.builder()
                .roomId(roomId)
                .messages(messages)
                .build());
    }

}
