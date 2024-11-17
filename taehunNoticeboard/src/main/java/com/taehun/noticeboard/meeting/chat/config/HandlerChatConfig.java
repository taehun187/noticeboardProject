package com.taehun.noticeboard.meeting.chat.config;

import com.taehun.noticeboard.meeting.chat.handler.WebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class HandlerChatConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;

    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(this.webSocketHandler, new String[]{"/chat"}).setAllowedOrigins(new String[]{"*"});
    }

    public HandlerChatConfig(final WebSocketHandler webSocketHandler) {
        this.webSocketHandler = webSocketHandler;
    }
}