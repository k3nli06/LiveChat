package com.k3nli.liveChat.persistence.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.k3nli.liveChat.persistence.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByName (String name);
}
