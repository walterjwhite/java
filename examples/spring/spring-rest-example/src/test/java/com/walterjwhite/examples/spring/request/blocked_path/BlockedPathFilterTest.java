package com.walterjwhite.examples.spring.request.blocked_path;

import static org.mockito.Mockito.*;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlockedPathFilterTest {

  private BlockedPathFilter filter;
  private HttpServletRequest req;
  private HttpServletResponse res;
  private jakarta.servlet.FilterChain chain;

  @BeforeEach
  void setUp() {
    req = mock(HttpServletRequest.class);
    res = mock(HttpServletResponse.class);
    chain = mock(jakarta.servlet.FilterChain.class);
  }

  @Test
  void allowsExcludedPath() throws Exception {
