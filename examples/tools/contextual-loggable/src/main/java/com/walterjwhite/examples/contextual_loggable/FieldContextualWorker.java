package com.walterjwhite.examples.contextual_loggable;

import com.walterjwhite.logging.FieldContextualLoggable;
import com.walterjwhite.logging.annotation.ContextualLoggableField;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class FieldContextualWorker implements FieldContextualLoggable {
  @ContextualLoggableField protected final String[] arguments;

  public void doSomething() {
    throw new RuntimeException("I don't feel like working today: " + System.currentTimeMillis());
  }
}
