package com.walterjwhite.examples.spring.batch_simple;

import com.walterjwhite.google.resilience4j.annotation.RateLimited;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/batch_simple")
@RequiredArgsConstructor
public class BatchController {
  private final BatchService batchService;
  private final SimpleBatchJobRepository simpleBatchJobRepository;

  @RateLimited
  @GetMapping("/jobs")
  public List<BatchJob> list() {
    return simpleBatchJobRepository.listJobs();
  }

  @RateLimited
  @GetMapping("/jobs/{id}")
  public ResponseEntity<BatchJob> get(@PathVariable("id") String id) {
    BatchJob j = simpleBatchJobRepository.getJob(id);
    if (j == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(j);
    }
  }

  @RateLimited
  @GetMapping("/exports/latest")
  public ResponseEntity<String> getLastExportFileName() {
    return ResponseEntity.ok(batchService.getLastExportFileName());
  }

  @RateLimited
  @PostMapping("/jobs/{id}/start")
  public ResponseEntity<Map<String, Object>> start(@PathVariable("id") String id) {
    batchService.startOrRerun(id);
    return ResponseEntity.ok(Map.of("started", true, "jobId", id));
  }

  @RateLimited
  @PostMapping("/jobs/{id}/cancel")
  public ResponseEntity<Map<String, Object>> cancel(@PathVariable("id") String id) {
    boolean ok = batchService.cancel(id);
    return ResponseEntity.ok(Map.of("cancelled", ok, "jobId", id));
  }
}
