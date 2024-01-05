package com.walterjwhite.spring.web.logging;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.SocketException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice(annotations = {RestController.class})
@Slf4j
public class SpringWebRestControllerExceptionAdvice {
  @ExceptionHandler({
    AccessDeniedException.class,
    HttpClientErrorException.Unauthorized.class,
    HttpClientErrorException.Forbidden.class,
    AccessException.class
  })
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<ErrorResponse<Object>> accessException(
      final Exception exception, final HttpServletRequest request) {
    return onException(exception, request, "Unauthorized", HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({HttpClientErrorException.NotFound.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ErrorResponse<Object>> notFound(
      final Exception exception, final HttpServletRequest request) {
    return onException(exception, request, "Not Found", HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({Exception.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorResponse<Object>> anyException(
      final Exception exception, final HttpServletRequest request) {
    return onException(
        exception, request, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({IOException.class, SocketException.class})
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public ResponseEntity<ErrorResponse<Object>> ioException(
      final Exception exception, final HttpServletRequest request) {
    return onException(exception, request, "Unavailable", HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler({
    HttpClientErrorException.BadRequest.class,
    HttpClientErrorException.MethodNotAllowed.class,
    HttpClientErrorException.NotAcceptable.class,
    HttpClientErrorException.UnsupportedMediaType.class,
    HttpClientErrorException.UnprocessableEntity.class,
    IllegalArgumentException.class
  })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse<Object>> badRequest(
      final Exception exception, final HttpServletRequest request) {
    return onException(exception, request, "Bad Request", HttpStatus.BAD_REQUEST);
  }

  private static ResponseEntity<ErrorResponse<Object>> onException(
      final Exception exception,
      final HttpServletRequest request,
      final String message,
      final HttpStatus httpStatus) {

    final ErrorResponse errorResponse = new ErrorResponse(message);
    final ExceptionLoggingFilter f =
        ApplicationHelper.getApplicationInstance()
            .getInjector()
            .getInstance(ExceptionLoggingFilter.class);

    LOGGER.error(
        "{}: request: {}, payload: {}",
        errorResponse.getId(),
        f.getRequestThreadLocal().get(),
        getRequestPayload(f));
    LOGGER.error(String.format("%s - %s", errorResponse.getId(), message), exception);
    return new ResponseEntity(errorResponse, httpStatus);
  }

  private static String getRequestPayload(final ExceptionLoggingFilter f) {
    if (f.getRequestWrapperThreadLocal().get() != null) {
      return new String(f.getRequestWrapperThreadLocal().get().getContentAsByteArray());
    }

    return null;
  }
}
