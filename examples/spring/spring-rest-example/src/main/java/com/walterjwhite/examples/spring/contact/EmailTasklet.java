package com.walterjwhite.examples.spring.contact;

import com.walterjwhite.examples.spring.request.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.StepContribution;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.infrastructure.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailTasklet implements Tasklet {
  private final EmailService emailService;

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext)
      throws Exception {
    var params = chunkContext.getStepContext().getStepExecution().getJobParameters();

    String from = params.getString("from");
    String subject = params.getString("subject");
    String name = params.getString("name");
    String email = params.getString("email");
    String message = params.getString("message");

    String body = name + " - " + email + " wrote:\r\n" + message;

    emailService.send(from, subject, body);

    return RepeatStatus.FINISHED;
  }
}
