package com.walterjwhite.examples.rest;

import jakarta.ws.rs.core.Response;
import java.util.Map;
import lombok.Data;
import lombok.ToString;

// public record WebTargetResponse<Output extends Object>(
//    Response response, Map rawResponse, Output parsedResponse) {}

@ToString(doNotUseGetters = true)
@Data
public class WebTargetResponse<Output extends Object> {
  protected final Response response;
  protected final Map rawResponse;
  protected final Output parsedResponse;
}
