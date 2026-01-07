package com.walterjwhite.queue.api.deprecated;


@Deprecated
public interface JobFilter {

  boolean accepts(final Object argument);
}
