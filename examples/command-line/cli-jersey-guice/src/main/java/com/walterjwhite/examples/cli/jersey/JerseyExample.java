package com.walterjwhite.examples.cli.jersey;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Path("jersey")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JerseyExample {
  protected final HelloService helloService;

  @GET
  @Path("/hi")
  @Produces(MediaType.TEXT_PLAIN)
  public String get() {
    return "hi";
  }

  @GET
  @Path("/hello/{name}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getHello(@PathParam("name") final String name) {
    return new Response(helloService.getMessage(name));
  }

  @Getter
  @RequiredArgsConstructor
  public class Response {
    protected final String message;
  }
}
