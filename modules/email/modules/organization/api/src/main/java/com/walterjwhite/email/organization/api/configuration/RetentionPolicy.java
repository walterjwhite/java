package com.walterjwhite.email.organization.api.configuration;

import java.util.concurrent.TimeUnit;


@Deprecated
public class RetentionPolicy {
  protected RetentionPolicy parentPolicy;
  protected int value;
  protected TimeUnit units;
}
