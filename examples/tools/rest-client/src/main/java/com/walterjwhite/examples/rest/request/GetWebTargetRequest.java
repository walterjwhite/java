package com.walterjwhite.examples.rest.request;

import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import java.util.function.Function;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class GetWebTargetRequest<Output extends Object> extends AbstractWebTargetRequest<Output> {
  public GetWebTargetRequest(
      final WebTarget webTarget,
      final MediaType mediaType,
      final Function<Map, Output> mapOutputFunction) {
    super(webTarget, mediaType, mapOutputFunction);
  }

  public Response invoke(final Invocation.Builder invocationBuilder) {
    return invocationBuilder.get();
  }
}
