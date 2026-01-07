package com.walterjwhite.examples.spring.async;

import com.walterjwhite.examples.spring.async.model.Request;
import com.walterjwhite.examples.spring.async.model.TaskInfo;
import com.walterjwhite.google.resilience4j.annotation.RateLimited;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/async")
@Slf4j
@RequiredArgsConstructor
public class AsyncController {
  private final AsyncTaskService service;

  @RateLimited
  @PostMapping("/submit")
  public ResponseEntity<?> submit(
      @RequestBody final Request req, final HttpServletRequest request, final Principal principal) {
    int duration = Math.max(0, req.getDurationSeconds());
    String identity = resolveIdentity(principal, request, req.getName());

    try {
      String id = service.submit(req.getName(), duration);
      LOGGER.info(
          "Task submitted id={} name={} duration={} by {}", id, req.getName(), duration, identity);
      return ResponseEntity.ok(Map.of("taskId", id));
    } catch (RequestNotPermitted e) {
      LOGGER.warn("task not submitted", e);
      return ResponseEntity.status(HttpStatusCode.valueOf(429)).build();
    }
  }

  @RateLimited
  @GetMapping("/queued")
  public List<TaskInfo> queued() {
    return service.listQueued();
  }

  @RateLimited
  @GetMapping("/running")
  public List<TaskInfo> running() {
    return service.listRunning();
  }

  @RateLimited
  @GetMapping("/all")
  public List<TaskInfo> all() {
    return service.listAll();
  }

  @RateLimited
  @GetMapping("/{id}")
  public ResponseEntity<TaskInfo> get(@PathVariable("id") String id) {
    TaskInfo t = service.get(id);
    if (t == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(t);
    }
  }

  @RateLimited
  @PostMapping("/{id}/cancel")
  public ResponseEntity<?> cancel(@PathVariable("id") String id) {
    TaskInfo t = service.cancel(id);
    if (t == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(
        Map.of(
            "taskId", t.getId(),
            "status", t.getStatus(),
            "submittedAt", t.getSubmittedAt(),
            "completedAt", t.getCompletedAt()));
  }

  @RateLimited
  @GetMapping("/debug")
  public ResponseEntity<Map<String, Object>> debug(
      HttpServletRequest request, Principal principal) {
    String identity = resolveIdentity(principal, request, null);
    return ResponseEntity.ok(
        Map.of(
            "identity", identity,
            "queuedCount", service.listQueued().size(),
            "runningCount", service.listRunning().size()));
  }

  private String resolveIdentity(
      Principal principal, HttpServletRequest request, String fallbackName) {
    if (principal != null && principal.getName() != null && !principal.getName().isBlank()) {
      return principal.getName();
    }
    String xff = request.getHeader("X-Forwarded-For");
    if (xff != null && !xff.isBlank()) {
      int comma = xff.indexOf(',');
      if (comma > 0) {
        return xff.substring(0, comma).trim();
      }
      return xff.trim();
    }
    if (request.getRemoteAddr() != null) {
      return request.getRemoteAddr();
    }

    if (fallbackName != null && !fallbackName.isBlank()) {
      return fallbackName;
    } else {
      return "unknown";
    }
  }
}
