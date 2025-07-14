package com.example.jaiBackend2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jaiBackend2.Entity.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatId(Long chatId);

}
