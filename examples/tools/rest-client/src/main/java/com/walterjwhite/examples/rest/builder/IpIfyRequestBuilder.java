package com.walterjwhite.examples.rest.builder;

import com.walterjwhite.examples.rest.request.GetWebTargetRequest;
import jakarta.ws.rs.core.MediaType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IpIfyRequestBuilder {
  public static GetWebTargetRequest build() {
    return new GetWebTargetRequest(
        RestClientBuilder.build().target("https://api.ipify.org?format=json"),
        MediaType.APPLICATION_JSON_TYPE,
        null);
  }
}
