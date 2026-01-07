package com.walterjwhite.examples.spring.batch_simple;

import jakarta.annotation.PostConstruct;
import java.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleBatchJobRepository {
  private final Map<String, BatchJob> jobs = new LinkedHashMap<>();

  @PostConstruct
  public void init() {
    jobs.put("1", new BatchJob("1", "Fetch Data", 4, Set.of()));
    jobs.put("2", new BatchJob("2", "Load", 5, Set.of("1")));
    jobs.put("3", new BatchJob("3", "Validate Accounts", 6, Set.of("2")));
    jobs.put("4", new BatchJob("4", "Validate Transactions", 3, Set.of("2")));
    jobs.put("5", new BatchJob("5", "Export", 5, Set.of("3", "4")));

    for (BatchJob job : jobs.values()) {
      for (String dep : job.getDependencies()) {
        BatchJob parent = jobs.get(dep);
        if (parent != null) {
          parent.addDependent(job.getId());
        }
      }
    }

    LOGGER.info("BatchService initialized with jobs: {}", jobs.keySet());
  }

  public synchronized List<BatchJob> listJobs() {
    return new ArrayList<>(jobs.values());
  }

  public synchronized BatchJob getJob(String id) {
    return jobs.get(id);
  }
}
