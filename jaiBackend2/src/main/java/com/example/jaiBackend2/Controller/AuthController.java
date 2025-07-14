package com.example.jaiBackend2.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jaiBackend2.Entity.User;
import com.example.jaiBackend2.Repository.UserRepository;
import com.example.jaiBackend2.dto.AuthRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private final PasswordEncoder encoder;

    /*public AuthController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder(passwordEncoder);
    }*/

   @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest req) {
        if (userRepo.findByUsername(req.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(encoder.encode(req.getPassword()));
        userRepo.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    
    @GetMapping("/me")
    public ResponseEntity<?> currentUser(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }
}
