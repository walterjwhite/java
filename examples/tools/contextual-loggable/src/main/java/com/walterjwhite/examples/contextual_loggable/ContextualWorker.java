package com.walterjwhite.examples.contextual_loggable;

import com.walterjwhite.logging.ContextualLoggable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class ContextualWorker implements ContextualLoggable {
  protected final String[] arguments;

  public void doSomething() {
    throw new RuntimeException("I don't feel like working today: " + System.currentTimeMillis());
  }

  public String printContextualInformation() {
    //        return "arguments:" + Arrays.toString(arguments);
    return toString();
  }
}
