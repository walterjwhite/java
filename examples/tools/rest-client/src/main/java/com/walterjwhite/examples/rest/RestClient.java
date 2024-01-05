package com.walterjwhite.examples.rest;

import com.walterjwhite.examples.rest.request.AbstractWebTargetRequest;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.Response;
import java.util.Map;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestClient {
  public <Output extends Object> WebTargetResponse<Output> call(
      final AbstractWebTargetRequest<Output> abstractWebTargetRequest) {
    final Invocation.Builder invocationBuilder =
        abstractWebTargetRequest.getWebTarget().request(abstractWebTargetRequest.getMediaType());
    abstractWebTargetRequest.prepare(invocationBuilder);

    // get, post, ...
    final Response response = abstractWebTargetRequest.invoke(invocationBuilder);

    final Map mapResponse = response.readEntity(Map.class);
    if (translateResponse(abstractWebTargetRequest, response)) {
      return new WebTargetResponse(
          response,
          mapResponse,
          abstractWebTargetRequest.getMapOutputFunction().apply(mapResponse));
    }

    return new WebTargetResponse(response, mapResponse, null);
  }

  protected boolean translateResponse(
      final AbstractWebTargetRequest abstractWebTargetRequest, final Response response) {
    return abstractWebTargetRequest.isSuccessful(response)
        && abstractWebTargetRequest.getMapOutputFunction() != null;
  }
}
