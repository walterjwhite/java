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
public abstract class AbstractWebTargetRequest<Output extends Object> {
  protected final WebTarget webTarget;
  protected final MediaType mediaType;
  protected final Function<Map, Output> mapOutputFunction;

  public void prepare(final Invocation.Builder invocationBuilder) {}

  public abstract Response invoke(final Invocation.Builder invocationBuilder);

  public boolean isSuccessful(final Response response) {
    return response.getStatus() >= 200 && response.getStatus() < 400;
  }
}
