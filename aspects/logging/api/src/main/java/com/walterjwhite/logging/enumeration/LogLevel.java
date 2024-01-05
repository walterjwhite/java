package com.walterjwhite.logging.enumeration;

import org.slf4j.Logger;

public enum LogLevel {
  TRACE {
    @Override
    public void log(final Logger logger, final String format, final Object... arguments) {
      logger.trace(format, arguments);
    }
  },
  DEBUG {
    @Override
    public void log(final Logger logger, final String format, final Object... arguments) {
      logger.debug(format, arguments);
    }
  },
  INFO {
    @Override
    public void log(final Logger logger, final String format, final Object... arguments) {
      logger.info(format, arguments);
    }
  },
  WARN {
    @Override
    public void log(final Logger logger, final String format, final Object... arguments) {
      logger.warn(format, arguments);
    }
  },
  ERROR {
    @Override
    public void log(final Logger logger, final String format, final Object... arguments) {
      logger.error(format, arguments);
    }
  };

  public abstract void log(final Logger logger, final String format, final Object... arguments);
}
