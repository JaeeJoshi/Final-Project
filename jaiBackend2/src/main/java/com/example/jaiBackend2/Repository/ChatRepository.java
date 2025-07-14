package com.example.jaiBackend2.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jaiBackend2.Entity.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long>{
    List<Chat> findByUserUsername(String Username);

    

}
