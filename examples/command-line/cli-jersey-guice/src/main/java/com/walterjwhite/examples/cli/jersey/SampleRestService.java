package com.walterjwhite.examples.cli.jersey;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/sample-rest-service")
public class SampleRestService {
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String getStatus() {
    return "success";
  }
}
