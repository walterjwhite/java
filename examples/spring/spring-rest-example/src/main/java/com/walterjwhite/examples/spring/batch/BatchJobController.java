package com.walterjwhite.examples.spring.batch;

import com.walterjwhite.examples.spring.batch.dto.JobExecutionDto;
import com.walterjwhite.examples.spring.batch.dto.JobInstanceInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping(path = "/api/batch", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class BatchJobController {

    private final BatchJobService batchJobService;

    @PostMapping(path = "/jobs/{jobName}/start", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JobExecutionDto> startJob(
            @PathVariable String jobName,
            @RequestBody(required = false) Map<String, String> params
    ) throws Exception {
        LOGGER.info("Request to start job '{}' with params {}", jobName, params);
        JobExecution execution = batchJobService.startJob(jobName, params == null ? new LinkedHashMap<>() : params);
        JobExecutionDto dto = JobExecutionDto.from(execution);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PostMapping("/executions/{id}/stop")
    public ResponseEntity<Map<String, Object>> stopExecution(@PathVariable("id") long executionId) throws Exception {
        LOGGER.info("Request to stop execution {}", executionId);
        boolean changed = batchJobService.stopExecution(executionId);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("executionId", executionId);
        body.put("stoppingRequested", changed);
        return ResponseEntity.ok(body);
    }

    @PostMapping("/executions/{id}/abandon")
    public ResponseEntity<Map<String, Object>> abandonExecution(@PathVariable("id") long executionId) throws Exception {
        LOGGER.info("Request to abandon execution {}", executionId);
        boolean changed = batchJobService.abandonExecution(executionId);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("executionId", executionId);
        body.put("abandoned", changed);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/jobs/{jobName}/registered")
    public ResponseEntity<Map<String, Object>> isJobRegistered(@PathVariable String jobName) {
        boolean registered = batchJobService.isJobRegistered(jobName);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("jobName", jobName);
        body.put("registered", registered);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/executions/{id}")
    public ResponseEntity<JobExecutionDto> getJobExecution(@PathVariable("id") long executionId) {
        JobExecution execution = batchJobService.getJobExecution(executionId);
        if (execution == null) {
            throw new IllegalArgumentException("Job Execution ID: " + executionId + " not found.");
        }
        return ResponseEntity.ok(JobExecutionDto.from(execution));
    }

    @GetMapping("/jobs/{jobName}/history")
    public ResponseEntity<List<JobInstanceInfo>> getJobHistory(
            @PathVariable String jobName,
            @RequestParam(name = "start", defaultValue = "0") int start,
            @RequestParam(name = "count", defaultValue = "20") int count
    ) {
        List<JobInstanceInfo> history = batchJobService.getJobHistory(jobName, start, count);
        return ResponseEntity.ok(history);
    }

    /*
     * Exception handlers to produce sensible HTTP responses.
     */
    @ExceptionHandler(NoSuchJobException.class)
    public ResponseEntity<Map<String, Object>> handleNoSuchJob(NoSuchJobException ex) {
        LOGGER.warn("No such job: {}", ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "No such job");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(IllegalArgumentException ex) {
        LOGGER.warn("Not found: {}", ex.getMessage());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "Not found");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        LOGGER.error("Unhandled exception in BatchJobController", ex);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error", "internal_error");
        body.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}