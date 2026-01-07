package com.walterjwhite.examples.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AllClassesLoadTest {

  private static final String[] CLASSES =
      new String[] {
        "com.walterjwhite.examples.spring.mock.TransactionType",
        "com.walterjwhite.examples.spring.contact.ContactForm",
        "com.walterjwhite.examples.spring.contact.ContactService",
        "com.walterjwhite.examples.spring.mvc.Person",
        "com.walterjwhite.examples.spring.mvc.PersonConfig",
        "com.walterjwhite.examples.spring.mvc.PersonService",
        "com.walterjwhite.examples.spring.mvc.PersonServiceImpl",
        "com.walterjwhite.examples.spring.mvc.PersonRepository",
        "com.walterjwhite.examples.spring.mvc.PersonController",
        "com.walterjwhite.examples.spring.mvc.MaxCapacityExceededException",
        "com.walterjwhite.examples.spring.request.RequestRowMapper",
        "com.walterjwhite.examples.spring.request.BlockedPathConfiguration",
        "com.walterjwhite.examples.spring.request.RequestRepository",
        "com.walterjwhite.examples.spring.security.AdminSecurityConfig",
        "com.walterjwhite.examples.spring.request.BlockedPathFilter",
        "com.walterjwhite.examples.spring.request.RequestFilterConfiguration",
        "com.walterjwhite.examples.spring.BeanCreationLogger",
        "com.walterjwhite.examples.spring.batch_simple.JobStatus",
        "com.walterjwhite.examples.spring.batch_simple.BatchService",
        "com.walterjwhite.examples.spring.batch_simple.BatchJobRepository",
        "com.walterjwhite.examples.spring.mock.TransactionGenerator",
        "com.walterjwhite.examples.spring.mock.TransactionStatus",
        "com.walterjwhite.examples.spring.batch_simple.BatchJob",
        "com.walterjwhite.examples.spring.mock.Transaction",
        "com.walterjwhite.examples.spring.batch_simple.BatchController",
        "com.walterjwhite.examples.spring.mock.CSVWriter",
        "com.walterjwhite.examples.spring.batch.JobParametersUtil",
        "com.walterjwhite.examples.spring.batch.BatchJobService",
        "com.walterjwhite.examples.spring.batch.BatchJobController",
        "com.walterjwhite.examples.spring.async.AsyncExceptionHandler",
        "com.walterjwhite.examples.spring.async.AsyncTaskService",
        "com.walterjwhite.examples.spring.async.AsyncController",
        "com.walterjwhite.examples.spring.chat.ChatMessage",
        "com.walterjwhite.examples.spring.chat.ChatService",
        "com.walterjwhite.examples.spring.batch.dto.JobInstanceInfo",
        "com.walterjwhite.examples.spring.batch.dto.JobExecutionDto",
        "com.walterjwhite.examples.spring.async.model.TaskInfo",
        "com.walterjwhite.examples.spring.batch.dto.StepExecutionInfo",
        "com.walterjwhite.examples.spring.batch.dto.JobExecutionInfo",
        "com.walterjwhite.examples.spring.batch.config.HandlerMappingDiagnostics",
        "com.walterjwhite.examples.spring.async.model.TaskStatus",
        "com.walterjwhite.examples.spring.batch.config.JobOperatorConfig",
        "com.walterjwhite.examples.spring.async.model.Request",
        "com.walterjwhite.examples.spring.batch.config.DailyTransactionsBatchConfig",
        "com.walterjwhite.examples.spring.chat.ChatController",
        "com.walterjwhite.examples.spring.chat.WebSocketConfig",
        "com.walterjwhite.examples.spring.rest.RestControllerTestSpringApplicationModule",
        "com.walterjwhite.examples.spring.batch.task.daily.Validate",
        "com.walterjwhite.examples.spring.batch.task.daily.GenerateSummaryReport",
        "com.walterjwhite.examples.spring.batch.task.daily.ProcessClientData",
        "com.walterjwhite.examples.spring.batch.task.daily.FetchTransactionalData",
      };

  @Test
  public void loadAllClassesAndCheck() {
    List<String> failed = new ArrayList<>();
    for (String cn : CLASSES) {
      try {
        Class<?> c = Class.forName(cn);
        assertNotNull(c, "Class should be loadable: " + cn);
        if (c.isEnum()) {
          Object[] values = c.getEnumConstants();
          assertNotNull(values, "Enum values should not be null: " + cn);
        }
        if (!c.isInterface() && !Modifier.isAbstract(c.getModifiers())) {
          assertTrue(c.toString().length() > 0);
        }
      } catch (Throwable t) {
        failed.add(cn + " -> " + t.getClass().getSimpleName() + ": " + t.getMessage());
      }
    }
    if (!failed.isEmpty()) {
      fail("Some classes failed to load or inspect:\n" + String.join("\n", failed));
    }
  }
}
