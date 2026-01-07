package com.walterjwhite.examples.spring.batch;

import com.walterjwhite.examples.spring.batch.dto.JobExecutionInfo;
import com.walterjwhite.examples.spring.batch.dto.JobInstanceInfo;
import com.walterjwhite.examples.spring.batch.dto.StepExecutionInfo;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.JobInstance;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@RequiredArgsConstructor
@Slf4j
public class BatchJobService {
  private final JobRepository jobRepository;
  private final JobOperator jobOperator;
  private final JobRegistry jobRegistry;

  private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

  public JobExecution startJob(final String jobName, final Map<String, String> params)
      throws Exception {
    final Job job = jobRegistry.getJob(jobName);
    if (job == null) {
      throw new NoSuchJobException("Job not registered: " + jobName);
    }

    return jobOperator.start(job, JobParametersUtil.toJobParameters(params, false));
  }

  public boolean stopExecution(final long jobExecutionId) {
    return stop(jobExecutionId, BatchStatus.STOPPING);
  }

  public boolean abandonExecution(long jobExecutionId) {
    return stop(jobExecutionId, BatchStatus.ABANDONED);
  }

  protected boolean stop(final long jobExecutionId, final BatchStatus batchStatus) {
    final JobExecution jobExecution = jobRepository.getJobExecution(jobExecutionId);
    if (jobExecution == null) {
      throw new IllegalArgumentException("Job Execution ID: " + jobExecutionId + " not found.");
    }

    if (jobExecution.isRunning()) {
      jobExecution.setStatus(batchStatus);
      jobRepository.update(jobExecution);

      LOGGER.warn("{} job: {}", batchStatus.name(), jobExecution);

      pushStatusSnapshot();

      return true;
    }

    LOGGER.warn("job: {} is not presently running", jobExecution);
    return false;
  }

  public boolean isJobRegistered(String jobName) {
    return jobRegistry.getJobNames().contains(jobName);
  }

  public JobExecution getJobExecution(long jobExecutionId) {
    return jobRepository.getJobExecution(jobExecutionId);
  }

  public List<JobInstanceInfo> getJobHistory(String jobName, int start, int count) {
    final List<JobInstance> jobInstances = jobRepository.getJobInstances(jobName, start, count);
    List<JobInstanceInfo> result = new ArrayList<>(jobInstances.size());

    for (JobInstance instance : jobInstances) {
      final List<JobExecution> executions = jobRepository.getJobExecutions(instance);
      final List<JobExecutionInfo> execInfos =
          executions.stream()
              .map(JobExecutionInfo::from)
              .sorted(Comparator.comparing(JobExecutionInfo::getStartTime).reversed())
              .collect(Collectors.toList());
      result.add(new JobInstanceInfo(instance.getInstanceId(), instance.getJobName(), execInfos));
    }

    return result;
  }

  public List<String> listJobNames() {
    return new ArrayList<>(jobRegistry.getJobNames());
  }

  public Object getJobSteps(String jobName) throws NoSuchJobException {
    final Job job = jobRegistry.getJob(jobName);
    if (job == null) {
      throw new NoSuchJobException("Job not registered: " + jobName);
    }

    try {
      Method m = job.getClass().getMethod("getStepNames");
      Object result = m.invoke(job);
      return result;
    } catch (NoSuchMethodException nsme) {
      try {
        Method m2 = job.getClass().getMethod("getSteps");
        Object result2 = m2.invoke(job);
        return result2;
      } catch (Exception e) {
        Map<String, Object> fallback = new LinkedHashMap<>();
        fallback.put("jobName", job.getName());
        fallback.put("definition", job.toString());
        return fallback;
      }
    } catch (Exception e) {
      Map<String, Object> fallback = new LinkedHashMap<>();
      fallback.put("jobName", job.getName());
      fallback.put("error", e.getMessage());
      return fallback;
    }
  }

  public List<Long> getJobExecutions(String jobName) throws NoSuchJobException {
    final Job job = jobRegistry.getJob(jobName);
    if (job == null) {
      throw new NoSuchJobException("Job not registered: " + jobName);
    }
    final Set<Long> running = jobOperator.getRunningExecutions(jobName);
    return new ArrayList<>(running);
  }

  public List<Map<String, Object>> cancelJob(String jobName) throws NoSuchJobException {
    final Job job = jobRegistry.getJob(jobName);
    if (job == null) {
      throw new NoSuchJobException("Job not registered: " + jobName);
    }

    final Set<Long> running = jobOperator.getRunningExecutions(jobName);
    final List<Map<String, Object>> results = new ArrayList<>();

    for (Long execId : running) {
      boolean requested = false;
      try {
        requested = stopExecution(execId);
      } catch (Exception e) {
        try {
          jobOperator.stop(execId);
          requested = true;
        } catch (Exception ex) {
          LOGGER.warn("Failed to stop execution {} via operator: {}", execId, ex.getMessage());
          requested = false;
        }
      }
      Map<String, Object> entry = new LinkedHashMap<>();
      entry.put("executionId", execId);
      entry.put("cancelRequested", requested);
      results.add(entry);
    }

    pushStatusSnapshot();

    return results;
  }

  /*
   * Step-level operations (best-effort):
   *
   * - startStep: will attempt to start the job with a job-parameter `startStep=<stepName>`.
   *   This requires your job/launcher to honor that parameter and start the flow from the step.
   *
   * - stopStep: attempts to find running StepExecution(s) for the given job/step name and mark them STOPPING.
   *
   * - getStepHistory: walks job instances + executions and collects StepExecution entries for the named step.
   */

  public Map<String, Object> startStep(String jobName, String stepName, Map<String, String> params)
      throws Exception {
    final Job job = jobRegistry.getJob(jobName);
    if (job == null) {
      throw new NoSuchJobException("Job not registered: " + jobName);
    }
    Map<String, String> p;
    if (params == null) {
      p = new LinkedHashMap<>();
    } else {
      p = new LinkedHashMap<>(params);
    }
    p.put("startStep", stepName);
    JobExecution jobExecution = jobOperator.start(job, JobParametersUtil.toJobParameters(p, false));
    Map<String, Object> result = new LinkedHashMap<>();
    if (jobExecution != null) {
      result.put("startedJobExecutionId", jobExecution.getId());
    } else {
      result.put("startedJobExecutionId", null);
    }
    result.put("startStep", stepName);
    pushStatusSnapshot();
    return result;
  }

  public Map<String, Object> stopStep(String jobName, String stepName) throws NoSuchJobException {
    final Job job = jobRegistry.getJob(jobName);
    if (job == null) {
      throw new NoSuchJobException("Job not registered: " + jobName);
    }
    final Set<Long> running = jobOperator.getRunningExecutions(jobName);
    final Map<String, Object> summary = new LinkedHashMap<>();
    final List<Map<String, Object>> entries = new ArrayList<>();
    for (Long execId : running) {
      JobExecution je = jobRepository.getJobExecution(execId);
      if (je == null) continue;
      for (Object o : je.getStepExecutions()) {
        StepExecution se = (StepExecution) o;
        if (se.getStepName().equals(stepName) && se.getJobExecution().isRunning()) {
          se.setStatus(BatchStatus.STOPPING);
          try {
            jobRepository.update(se);
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("executionId", execId);
            entry.put("stepExecutionId", se.getId());
            entry.put("stopRequested", true);
            entries.add(entry);
          } catch (Exception e) {
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("executionId", execId);
            entry.put("stepExecutionId", se.getId());
            entry.put("stopRequested", false);
            entry.put("error", e.getMessage());
            entries.add(entry);
          }
        }
      }
    }
    summary.put("jobName", jobName);
    summary.put("stepName", stepName);
    summary.put("results", entries);

    pushStatusSnapshot();

    return summary;
  }

  public List<StepExecutionInfo> getStepHistory(
      String jobName, String stepName, int start, int count) {
    final List<JobInstance> jobInstances = jobRepository.getJobInstances(jobName, start, count);
    List<StepExecutionInfo> result = new ArrayList<>();
    for (JobInstance instance : jobInstances) {
      final List<JobExecution> executions = jobRepository.getJobExecutions(instance);
      for (JobExecution je : executions) {
        for (Object o : je.getStepExecutions()) {
          StepExecution se = (StepExecution) o;
          if (se.getStepName().equals(stepName)) {
            result.add(StepExecutionInfo.from(se));
          }
        }
      }
    }
    result.sort(
        Comparator.comparing(
            StepExecutionInfo::getStartTime, Comparator.nullsLast(Comparator.reverseOrder())));
    return result;
  }


  public void registerEmitter(SseEmitter emitter) {
    emitters.add(emitter);
    emitter.onCompletion(() -> emitters.remove(emitter));
    emitter.onTimeout(() -> emitters.remove(emitter));
    try {
      emitter.send(SseEmitter.event().name("snapshot").data(buildStatusSnapshot()));
    } catch (Exception e) {
    }
  }

  public Map<String, Object> buildStatusSnapshot() {
    Map<String, Object> snapshot = new LinkedHashMap<>();
    List<Map<String, Object>> jobsList = new ArrayList<>();
    for (String jobName : jobRegistry.getJobNames()) {
      Map<String, Object> jmap = new LinkedHashMap<>();
      jmap.put("jobName", jobName);
      try {
        Set<Long> running = jobOperator.getRunningExecutions(jobName);
        jmap.put("runningExecutionIds", new ArrayList<>(running));
      } catch (Exception e) {
        jmap.put("runningExecutionIds", Collections.emptyList());
      }
      try {
        List<JobInstance> instances = jobRepository.getJobInstances(jobName, 0, 1);
        if (instances != null && !instances.isEmpty()) {
          JobInstance inst = instances.get(0);
          List<JobExecution> execs = jobRepository.getJobExecutions(inst);
          if (execs != null && !execs.isEmpty()) {
            JobExecution last =
                execs.stream()
                    .max(
                        Comparator.comparing(
                            JobExecution::getStartTime,
                            Comparator.nullsLast(Comparator.naturalOrder())))
                    .orElse(null);
            if (last != null) {
              Map<String, Object> le = new LinkedHashMap<>();
              le.put("executionId", last.getId());
              if (last.getStatus() != null) {
                le.put("status", last.getStatus().toString());
              } else {
                le.put("status", null);
              }
              jmap.put("lastExecution", le);
            }
          }
        }
      } catch (Exception e) {
      }
      try {
        Object stepsPayload = getJobSteps(jobName);
        List<Map<String, Object>> steps = new ArrayList<>();
        if (stepsPayload instanceof String) {
        } else if (stepsPayload instanceof Object[]) {
          for (Object o : (Object[]) stepsPayload) {
            String n;
            if (o == null) {
              n = "";
            } else {
              if (o instanceof String) {
                n = (String) o;
              } else {
                n = o.toString();
              }
            }
            Map<String, Object> sm = new LinkedHashMap<>();
            sm.put("name", n);
            steps.add(sm);
          }
        } else if (stepsPayload instanceof java.util.Collection) {
          for (Object o : (Collection) stepsPayload) {
            String n;
            if (o == null) {
              n = "";
            } else {
              if (o instanceof String) {
                n = (String) o;
              } else if (o instanceof Map) {
                n = String.valueOf(((Map) o).get("name"));
              } else {
                n = o.toString();
              }
            }
            Map<String, Object> sm = new LinkedHashMap<>();
            sm.put("name", n);
            steps.add(sm);
          }
        }
        if (!steps.isEmpty()) jmap.put("steps", steps);
      } catch (Exception e) {
      }
      jobsList.add(jmap);
    }
    snapshot.put("jobs", jobsList);
    return snapshot;
  }

  public void pushStatusSnapshot() {
    Map<String, Object> snapshot = buildStatusSnapshot();
    for (SseEmitter emitter : emitters) {
      try {
        emitter.send(SseEmitter.event().name("snapshot").data(snapshot));
      } catch (Exception e) {
        emitters.remove(emitter);
      }
    }
  }
}
