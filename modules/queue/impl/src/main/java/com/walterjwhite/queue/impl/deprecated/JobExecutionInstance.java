package com.walterjwhite.queue.impl.deprecated;

import com.walterjwhite.queue.api.model.JobExecution;
import java.util.concurrent.Future;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JobExecutionInstance {
  protected final JobExecution jobExecution;
  protected final Future future;
}
