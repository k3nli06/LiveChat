package com.k3nli.liveChat.persistence.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.k3nli.liveChat.persistence.entity.ChatMessages;

@Repository
public interface ChatRepository extends MongoRepository<ChatMessages, String> {
    
    @Query(value="{roomId:'?0'}", fields="{'_id' : 0}")
    public List<ChatMessages> findHistoryMessages(String roomId);
}
