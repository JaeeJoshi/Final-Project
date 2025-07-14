package com.example.jaiBackend2.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

    private String content;
    private boolean isUser;
    private LocalDateTime timestamp = LocalDateTime.now();

    @ManyToOne
    private Chat chat;

    public Message() {
    }

    public Message(Long id, String content, boolean isUser, LocalDateTime timestamp, Chat chat) {
        this.id = id;
        this.content = content;
        this.isUser = isUser;
        this.timestamp = timestamp;
        this.chat = chat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean isUser) {
        this.isUser = isUser;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    

}
