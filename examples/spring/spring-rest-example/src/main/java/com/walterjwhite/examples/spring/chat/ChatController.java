package com.walterjwhite.examples.spring.chat;

import com.walterjwhite.examples.spring.limit.RateLimitingService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final RateLimitingService rateLimitingService;

    public ChatController(SimpMessagingTemplate messagingTemplate, RateLimitingService rateLimitingService) {
        this.messagingTemplate = messagingTemplate;
        this.rateLimitingService = rateLimitingService;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        String sessionId = headerAccessor != null ? headerAccessor.getSessionId() : "unknown-session";
        String identity = (message != null && message.getFrom() != null && !message.getFrom().trim().isEmpty())
                ? message.getFrom().trim()
                : sessionId;

        boolean globalOk = rateLimitingService.tryConsumeGlobal();
        boolean identityOk = rateLimitingService.tryConsumeForIdentity(identity);

        if (!globalOk || !identityOk) {
            ChatMessage sys = new ChatMessage("SYSTEM", "Messages from '" + identity + "' are being rate limited.", System.currentTimeMillis(), "SYSTEM");
            messagingTemplate.convertAndSend("/topic/public", sys);
            return;
        }

        if (message.getTimestamp() == 0) {
            message.setTimestamp(System.currentTimeMillis());
        }
        messagingTemplate.convertAndSend("/topic/public", message);
    }

    @MessageMapping("/chat.join")
    public void join(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
        if (message.getTimestamp() == 0) {
            message.setTimestamp(System.currentTimeMillis());
        }
        message.setType("JOIN");
        messagingTemplate.convertAndSend("/topic/public", message);
    }
}