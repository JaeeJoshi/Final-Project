package com.example.jaiBackend2.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jaiBackend2.Entity.Chat;
import com.example.jaiBackend2.Entity.Message;
import com.example.jaiBackend2.Repository.ChatRepository;
import com.example.jaiBackend2.Repository.MessageRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/message")
@RequiredArgsConstructor
public class MessageController {

    private final MessageRepository messageRepo;
    private final ChatRepository chatRepo;

     // Send a message to a chat
     @PostMapping("/send")
      public ResponseEntity<?> sendMessage(@RequestParam Long chatId, @RequestParam String content) {
        Chat chat = chatRepo.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found"));

        Message msg = new Message();
        msg.setContent(content);
        msg.setUser(true);
        msg.setChat(chat);
        messageRepo.save(msg);

        // Optional: Add simple auto-reply
        Message reply = new Message();
        reply.setContent("Auto-reply: " + content);
        reply.setUser(false);
        reply.setChat(chat);
        messageRepo.save(reply);

        return ResponseEntity.ok(List.of(msg, reply));
    }

    // Get all messages of a chat
    @GetMapping("/{chatId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long chatId) {
        return ResponseEntity.ok(messageRepo.findByChatId(chatId));
    }

    // Update a message
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMessage(@PathVariable Long id, @RequestBody String newContent) {
        Message msg = messageRepo.findById(id).orElseThrow(() -> new RuntimeException("Message not found"));
        msg.setContent(newContent);
        messageRepo.save(msg);
        return ResponseEntity.ok(msg);
    }


}
