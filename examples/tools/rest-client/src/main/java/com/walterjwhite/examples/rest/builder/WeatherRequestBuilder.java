package com.walterjwhite.examples.rest.builder;

import com.walterjwhite.examples.rest.request.GetWebTargetRequest;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.glassfish.jersey.client.ClientConfig;

// this doesn't produce JSON
@Deprecated
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeatherRequestBuilder {
  public static GetWebTargetRequest build() {
    return new GetWebTargetRequest(
        ClientBuilder.newClient(new ClientConfig()).target("http://wttr.in/"),
        MediaType.APPLICATION_JSON_TYPE,
        null);
  }
}
