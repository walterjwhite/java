package com.walterjwhite.examples.spring.async;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class AsyncControllerTest {

  private AsyncTaskService service;
  private AsyncController controller;
  private HttpServletRequest servletRequest;
  private Principal principal;

  @BeforeEach
  public void setup() {
    service = Mockito.mock(AsyncTaskService.class);
    controller = new AsyncController(service);
    servletRequest = Mockito.mock(HttpServletRequest.class);
    principal = null;
  }



}
