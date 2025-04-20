package com.k3nli.liveChat.persistence.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.k3nli.liveChat.persistence.entity.UserEntity;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findUserByName (String name);
}
