package com.codefellows.authdemo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long>
{
    ChatUser findByUsername(String username);
}
