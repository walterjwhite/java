package com.walterjwhite.examples.rest.request;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import java.util.function.Function;

public class PostWebTargetRequest<Output extends Object> extends AbstractWebTargetRequest<Output> {
  protected final Entity requestData;

  public PostWebTargetRequest(
      final WebTarget webTarget,
      final MediaType mediaType,
      final Function<Map, Output> mapOutputFunction,
      final Entity requestData) {
    super(webTarget, mediaType, mapOutputFunction);

    this.requestData = requestData;
  }

  public Response invoke(final Invocation.Builder invocationBuilder) {
    return invocationBuilder.post(requestData);
  }
}
