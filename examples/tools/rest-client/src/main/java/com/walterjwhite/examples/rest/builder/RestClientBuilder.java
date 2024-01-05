package com.walterjwhite.examples.rest.builder;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.glassfish.jersey.client.ClientConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RestClientBuilder {
  public static Client build() {
    return ClientBuilder.newClient(new ClientConfig());
  }
}
