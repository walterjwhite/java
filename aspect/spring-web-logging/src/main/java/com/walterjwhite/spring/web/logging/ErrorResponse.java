package com.walterjwhite.spring.web.logging;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse<Type> implements Serializable {
  private static final long serialVersionUID = 5L;
  protected final String id = RequestIdUtil.generate();
  protected final String description;
}
