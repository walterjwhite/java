package com.walterjwhite.examples.rest;

import jakarta.ws.rs.core.Response;
import java.util.Map;
import lombok.Data;
import lombok.ToString;


@ToString(doNotUseGetters = true)
@Data
public class WebTargetResponse<Output extends Object> {
  protected final Response response;
  protected final Map rawResponse;
  protected final Output parsedResponse;
}
