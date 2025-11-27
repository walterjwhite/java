package com.walterjwhite.examples.spring.batch_simple;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/batch_simple")
@RequiredArgsConstructor
public class BatchController {
    private final BatchService batchService;

    @GetMapping("/jobs")
    public List<BatchJob> list() {
        return batchService.listJobs();
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<BatchJob> get(@PathVariable("id") String id) {
        BatchJob j = batchService.getJob(id);
        return j == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(j);
    }

    @PostMapping("/jobs/{id}/start")
    public ResponseEntity<Map<String,Object>> start(@PathVariable("id") String id) {
        batchService.start(id);
        return ResponseEntity.ok(Map.of("started", true, "jobId", id));
    }

    @PostMapping("/jobs/{id}/cancel")
    public ResponseEntity<Map<String,Object>> cancel(@PathVariable("id") String id) {
        boolean ok = batchService.cancel(id);
        return ResponseEntity.ok(Map.of("cancelled", ok, "jobId", id));
    }

    @PostMapping("/jobs/{id}/rerun")
    public ResponseEntity<Map<String,Object>> rerun(@PathVariable("id") String id) {
        batchService.rerun(id);
        return ResponseEntity.ok(Map.of("rerunScheduled", true, "jobId", id));
    }
}