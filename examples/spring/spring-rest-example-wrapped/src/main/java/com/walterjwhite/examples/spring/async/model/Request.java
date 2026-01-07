package com.walterjwhite.examples.spring.async.model;

import lombok.Data;

@Data
public class Request {
  private String name;
  private int durationSeconds = 10;
}
