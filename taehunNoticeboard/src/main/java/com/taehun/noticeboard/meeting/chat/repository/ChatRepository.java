package com.taehun.noticeboard.meeting.chat.repository;

import com.taehun.noticeboard.meeting.chat.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long>, CustomChatRepository {
}