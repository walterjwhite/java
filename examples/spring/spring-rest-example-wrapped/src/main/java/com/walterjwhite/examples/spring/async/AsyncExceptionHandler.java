package com.walterjwhite.examples.spring.async;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class AsyncExceptionHandler {

  @ExceptionHandler(RequestNotPermitted.class)
  public ResponseEntity<Map<String, Object>> handleRequestNotPermitted(RequestNotPermitted ex) {
    LOGGER.warn("Rate limit exceeded: {}", ex.toString());
    Map<String, Object> body =
        Map.of(
            "error",
            "Too Many Requests",
            "message",
            ex.getMessage() == null ? "" : ex.getMessage());
    return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(body);
  }
}
