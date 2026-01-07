package com.walterjwhite.examples.spring.request.blocked_path;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

public class BlockedPathFilter extends OncePerRequestFilter {
  private final List<String> blockedPatterns;
  private final List<String> excludePatterns;
  private final AntPathMatcher matcher = new AntPathMatcher();

  public BlockedPathFilter(List<String> blockedPatterns) {
    this(blockedPatterns, List.of());
  }

  public BlockedPathFilter(List<String> blockedPatterns, List<String> excludePatterns) {
    this.blockedPatterns =
        blockedPatterns == null
            ? List.of()
            : blockedPatterns.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toUnmodifiableList());
    this.excludePatterns =
        excludePatterns == null
            ? List.of()
            : excludePatterns.stream()
                .filter(Objects::nonNull)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toUnmodifiableList());
  }

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    if (request.getDispatcherType() != DispatcherType.REQUEST) {
      filterChain.doFilter(request, response);
      return;
    }

    final String path = request.getRequestURI();

    for (String ex : excludePatterns) {
      if (matches(ex, path)) {
        filterChain.doFilter(request, response);
        return;
      }
    }

    for (String p : blockedPatterns) {
      if (matches(p, path)) {
        if (response.isCommitted()) {
          response.sendError(HttpServletResponse.SC_NOT_FOUND);
          return;
        }

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpServletResponse.SC_NOT_FOUND);
        request.setAttribute(RequestDispatcher.ERROR_MESSAGE, "Not Found");
        request.setAttribute(RequestDispatcher.ERROR_REQUEST_URI, path);

        RequestDispatcher rd = request.getRequestDispatcher("/error");
        rd.forward(request, response);
        return;
      }
    }
    filterChain.doFilter(request, response);
  }

  private boolean matches(String pattern, String path) {
    if (pattern == null || pattern.isEmpty()) return false;
    try {
      return matcher.match(pattern, path)
          || matcher.match(pattern, path.substring(Math.min(1, path.length())));
    } catch (Exception e) {
      return false;
    }
  }
}
