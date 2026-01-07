package com.walterjwhite.examples.spring.batch;

import com.walterjwhite.examples.spring.batch.dto.JobExecutionDto;
import com.walterjwhite.examples.spring.batch.dto.JobInstanceInfo;
import com.walterjwhite.examples.spring.batch.dto.StepExecutionInfo;
import com.walterjwhite.google.resilience4j.annotation.RateLimited;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping(path = "/api/batch", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class BatchJobController {

  private final BatchJobService batchJobService;

  @RateLimited
  @PostMapping(path = "/jobs/{jobName}/start", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<JobExecutionDto> startJob(
      @PathVariable("jobName") String jobName,
      @RequestBody(required = false) Map<String, String> params)
      throws Exception {
    LOGGER.info("Request to start job '{}' with params {}", jobName, params);
    Map<String, String> p;
    if (params == null) {
      p = new LinkedHashMap<>();
    } else {
      p = params;
    }
    JobExecution execution = batchJobService.startJob(jobName, p);
    JobExecutionDto dto = JobExecutionDto.from(execution);
    return ResponseEntity.status(HttpStatus.CREATED).body(dto);
  }

  @RateLimited
  @PostMapping("/executions/{id}/stop")
  public ResponseEntity<Map<String, Object>> stopExecution(@PathVariable("id") long executionId) {
    LOGGER.info("Request to stop execution {}", executionId);
    boolean changed = batchJobService.stopExecution(executionId);
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("executionId", executionId);
    body.put("stoppingRequested", changed);
    return ResponseEntity.ok(body);
  }

  @RateLimited
  @PostMapping("/executions/{id}/abandon")
  public ResponseEntity<Map<String, Object>> abandonExecution(
      @PathVariable("id") long executionId) {
    LOGGER.info("Request to abandon execution {}", executionId);
    boolean changed = batchJobService.abandonExecution(executionId);
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("executionId", executionId);
    body.put("abandoned", changed);
    return ResponseEntity.ok(body);
  }

  @RateLimited
  @GetMapping("/jobs/{jobName}/registered")
  public ResponseEntity<Map<String, Object>> isJobRegistered(
      @PathVariable("jobName") String jobName) {
    boolean registered = batchJobService.isJobRegistered(jobName);
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("jobName", jobName);
    body.put("registered", registered);
    return ResponseEntity.ok(body);
  }

  @RateLimited
  @GetMapping("/executions/{id}")
  public ResponseEntity<JobExecutionDto> getJobExecution(
      @PathVariable("executionId") long executionId) {
    JobExecution execution = batchJobService.getJobExecution(executionId);
    if (execution == null) {
      throw new IllegalArgumentException("Job Execution ID: " + executionId + " not found.");
    }
    return ResponseEntity.ok(JobExecutionDto.from(execution));
  }

  @RateLimited
  @GetMapping("/jobs/{jobName}/history")
  public ResponseEntity<List<JobInstanceInfo>> getJobHistory(
      @PathVariable("jobName") String jobName,
      @RequestParam(name = "start", defaultValue = "0") int start,
      @RequestParam(name = "count", defaultValue = "20") int count) {
    List<JobInstanceInfo> history = batchJobService.getJobHistory(jobName, start, count);
    return ResponseEntity.ok(history);
  }

  @RateLimited
  @GetMapping("/jobs")
  public ResponseEntity<List<String>> listRegisteredJobs() {
    List<String> jobs = batchJobService.listJobNames();
    return ResponseEntity.ok(jobs);
  }

  @RateLimited
  @GetMapping("/jobs/{jobName}/steps")
  public ResponseEntity<Object> getJobSteps(@PathVariable("jobName") String jobName)
      throws NoSuchJobException {
    Object payload = batchJobService.getJobSteps(jobName);
    return ResponseEntity.ok(payload);
  }

  @RateLimited
  @GetMapping("/jobs/{jobName}/executions")
  public ResponseEntity<List<Long>> getJobExecutions(@PathVariable("jobName") String jobName)
      throws NoSuchJobException {
    List<Long> ids = batchJobService.getJobExecutions(jobName);
    return ResponseEntity.ok(ids);
  }

  @RateLimited
  @PostMapping("/jobs/{jobName}/cancel")
  public ResponseEntity<List<Map<String, Object>>> cancelJob(
      @PathVariable("jobName") String jobName) throws NoSuchJobException {
    List<Map<String, Object>> results = batchJobService.cancelJob(jobName);
    return ResponseEntity.ok(results);
  }

  @RateLimited
  @PostMapping(
      path = "/jobs/{jobName}/steps/{stepName}/start",
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Map<String, Object>> startStep(
      @PathVariable("jobName") String jobName,
      @PathVariable("stepName") String stepName,
      @RequestBody(required = false) Map<String, String> params)
      throws Exception {
    LOGGER.info(
        "Request to start step '{}' for job '{}' with params {}", stepName, jobName, params);
    Map<String, String> p2;
    if (params == null) {
      p2 = new LinkedHashMap<>();
    } else {
      p2 = params;
    }
    Map<String, Object> result = batchJobService.startStep(jobName, stepName, p2);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
  }

  @RateLimited
  @PostMapping("/jobs/{jobName}/steps/{stepName}/stop")
  public ResponseEntity<Map<String, Object>> stopStep(
      @PathVariable("jobName") String jobName, @PathVariable("stepName") String stepName)
      throws NoSuchJobException {
    LOGGER.info("Request to stop step '{}' for job '{}'", stepName, jobName);
    Map<String, Object> res = batchJobService.stopStep(jobName, stepName);
    return ResponseEntity.ok(res);
  }

  @RateLimited
  @GetMapping("/jobs/{jobName}/steps/{stepName}/history")
  public ResponseEntity<List<StepExecutionInfo>> getStepHistory(
      @PathVariable("jobName") String jobName,
      @PathVariable("stepName") String stepName,
      @RequestParam(name = "start", defaultValue = "0") int start,
      @RequestParam(name = "count", defaultValue = "50") int count) {
    List<StepExecutionInfo> history =
        batchJobService.getStepHistory(jobName, stepName, start, count);
    return ResponseEntity.ok(history);
  }

  @RateLimited
  @GetMapping(path = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter streamAll() {
    final SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
    batchJobService.registerEmitter(emitter);
    return emitter;
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
  public ResponseEntity<Map<String, Object>> handleIllegalArg(IllegalArgumentException ex) {
    LOGGER.warn("Bad request: {}", ex.getMessage());
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("error", "Bad request");
    body.put("message", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }
}
