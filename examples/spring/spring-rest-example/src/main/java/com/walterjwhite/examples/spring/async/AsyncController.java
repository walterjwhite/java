package com.walterjwhite.examples.spring.async;

import com.walterjwhite.examples.spring.limit.RateLimitingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/async")
@RequiredArgsConstructor
public class AsyncController {

    private static final Logger log = LoggerFactory.getLogger(AsyncController.class);

    private final AsyncTaskService service;
    private final RateLimitingService rateLimiter;

    @PostMapping("/submit")
    public ResponseEntity<?> submit(@RequestBody SubmitRequest req, HttpServletRequest request, Principal principal) {
        int duration = Math.max(0, req.getDurationSeconds());
        String identity = resolveIdentity(principal, request, req.getName());

        boolean globalOk = rateLimiter.tryConsumeGlobal();
        boolean identityOk = rateLimiter.tryConsumeForIdentity(identity);

        double globalTokens = rateLimiter.getGlobalTokens();
        double identityTokens = rateLimiter.getTokensForIdentity(identity);

        log.info("Submit request[name={},duration={},identity={}] -> globalOk={} (tokens={}), identityOk={} (tokens={})",
                req.getName(), duration, identity, globalOk, globalTokens, identityOk, identityTokens);

        if (!globalOk || !identityOk) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of(
                            "error", "Rate limit exceeded",
                            "allowed", false,
                            "identity", identity,
                            "globalTokens", globalTokens,
                            "identityTokens", identityTokens
                    ));
        }

        String id = service.submit(req.getName(), duration);
        log.info("Task submitted id={} name={} duration={} by {}", id, req.getName(), duration, identity);
        return ResponseEntity.ok(Map.of("taskId", id));
    }

    @GetMapping("/queued")
    public List<TaskInfo> queued() {
        return service.listQueued();
    }

    @GetMapping("/running")
    public List<TaskInfo> running() {
        return service.listRunning();
    }

    @GetMapping("/all")
    public List<TaskInfo> all() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskInfo> get(@PathVariable("id") String id) {
        TaskInfo t = service.get(id);
        return t == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(t);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@PathVariable("id") String id) {
        TaskInfo t = service.cancel(id);
        if (t == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Map.of(
                "taskId", t.getId(),
                "status", t.getStatus(),
                "submittedAt", t.getSubmittedAt(),
                "completedAt", t.getCompletedAt()
        ));
    }

    @GetMapping("/debug")
    public ResponseEntity<Map<String,Object>> debug(HttpServletRequest request, Principal principal) {
        String identity = resolveIdentity(principal, request, null);
        return ResponseEntity.ok(Map.of(
                "identity", identity,
                "queuedCount", service.listQueued().size(),
                "runningCount", service.listRunning().size(),
                "globalTokens", rateLimiter.getGlobalTokens(),
                "identityTokens", rateLimiter.getTokensForIdentity(identity)
        ));
    }

    private String resolveIdentity(Principal principal, HttpServletRequest request, String fallbackName) {
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
        if (request.getRemoteAddr() != null) return request.getRemoteAddr();
        return (fallbackName != null && !fallbackName.isBlank()) ? fallbackName : "unknown";
    }

    public static class SubmitRequest {
        private String name;
        private int durationSeconds = 10;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getDurationSeconds() { return durationSeconds; }
        public void setDurationSeconds(int durationSeconds) { this.durationSeconds = durationSeconds; }
    }
}