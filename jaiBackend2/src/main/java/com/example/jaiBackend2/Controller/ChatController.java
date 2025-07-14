package com.example.jaiBackend2.Controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jaiBackend2.Entity.Chat;
import com.example.jaiBackend2.Entity.User;
import com.example.jaiBackend2.Repository.ChatRepository;
import com.example.jaiBackend2.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatRepository chatRepo;
    private final UserRepository userRepo;

    @PostMapping("/new")
    public ResponseEntity<?> createChat(@RequestParam String title, Principal principal) {
        User user = userRepo.findByUsername(principal.getName()).orElseThrow();
        Chat chat = new Chat();
        chat.setTitle(title);
        chat.setUser(user);
        chatRepo.save(chat);
        return ResponseEntity.ok(chat);
    }

     // Get all chats for the logged-in user
    @GetMapping
    public ResponseEntity<Object> getAllChats(Principal principal) {
        return ResponseEntity.ok(chatRepo.findByUserUsername(principal.getName()));
    }

     // Delete a specific chat
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable Long id, Principal principal) {
        Chat chat = chatRepo.findById(id).orElseThrow(() -> new RuntimeException("Chat not found"));
        if (!chat.getUser().getUsername().equals(principal.getName())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        chatRepo.delete(chat);
        return ResponseEntity.ok("Deleted");
    }

}
