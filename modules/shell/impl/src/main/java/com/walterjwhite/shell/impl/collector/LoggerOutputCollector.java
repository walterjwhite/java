package com.walterjwhite.shell.impl.collector;

import com.walterjwhite.logging.annotation.NonLoggable;
import com.walterjwhite.shell.api.service.OutputCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerOutputCollector implements OutputCollector {
  protected final Logger logger;

  public LoggerOutputCollector(final Class targetClass) {

    this.logger = LoggerFactory.getLogger(targetClass);
  }

  public LoggerOutputCollector(final String name) {

    this.logger = LoggerFactory.getLogger(name);
  }

  public LoggerOutputCollector(final Class targetClass, final String name) {

    this.logger = LoggerFactory.getLogger(targetClass + "(" + name + ")");
  }

  
  @NonLoggable
  @Override
  public void onData(String line, boolean isError) {
    if (isError) {
      logger.error(line);
    } else {
      logger.info(line);
    }
  }

  @Override
  public String toString() {
    return getClass().getName() + "{" + logger.getName() + "}";
  }
}
