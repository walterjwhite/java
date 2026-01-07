package com.walterjwhite.examples.spring.chat;

import com.walterjwhite.google.resilience4j.annotation.RateLimited;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {
  private final SimpMessagingTemplate messagingTemplate;

  @RateLimited
  @MessageMapping("/chat.send")
  public void sendMessage(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
    String sessionId = headerAccessor != null ? headerAccessor.getSessionId() : "unknown-session";
    String identity =
        (message != null && message.getFrom() != null && !message.getFrom().trim().isEmpty())
            ? message.getFrom().trim()
            : sessionId;

    if (message.getTimestamp() == 0) {
      message.setTimestamp(System.currentTimeMillis());
    }
    messagingTemplate.convertAndSend("/topic/public", message);
  }

  @RateLimited
  @MessageMapping("/chat.join")
  public void join(ChatMessage message, SimpMessageHeaderAccessor headerAccessor) {
    if (message.getTimestamp() == 0) {
      message.setTimestamp(System.currentTimeMillis());
    }
    message.setType("JOIN");
    messagingTemplate.convertAndSend("/topic/public", message);
  }
}
