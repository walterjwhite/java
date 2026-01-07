package com.walterjwhite.examples.spring.chat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class ChatService {
  private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

  public SseEmitter subscribe() {
    SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    emitters.add(emitter);

    emitter.onCompletion(() -> emitters.remove(emitter));
    emitter.onTimeout(() -> emitters.remove(emitter));
    emitter.onError((e) -> emitters.remove(emitter));

    try {
      emitter.send(SseEmitter.event().name("system").data("connected"));
    } catch (IOException ignored) {
    }

    return emitter;
  }

  public void broadcast(ChatMessage message) {
    List<SseEmitter> dead = new ArrayList<>();
    for (SseEmitter emitter : emitters) {
      try {
        emitter.send(SseEmitter.event().name("chat-message").data(message));
      } catch (Exception e) {
        dead.add(emitter);
      }
    }
    emitters.removeAll(dead);
  }
}
