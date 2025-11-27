package com.walterjwhite.modules.web.service.sample;

import com.google.inject.Injector;
import com.walterjwhite.infrastructure.metrics.api.annotation.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample2")
public class SampleWebService2 {
  protected final SampleService sampleService;
  protected final Injector injector;

  @Inject
  public SampleWebService2(SampleService sampleService, Injector injector) {
    this.sampleService = sampleService;
    this.injector = injector;
  }

  @Counter
  @Deprecated
  @GET
  @Produces("text/plain")
  @Path("/bad/sayHello")
  public String sayHelloBad(@QueryParam("name") final String name) {
    return sampleService.sayHello(name);
  }

  @Counter
  @Operation(summary = "say hello", description = "sample restful webservice")
  @GET
  @Produces("text/plain")
  @Path("/good/sayHello/{name}")
  public String sayHelloGood(@PathParam("name") final String name) {
    return sampleService.sayHello(name);
  }

  @Counter
  @Operation(summary = "greet", description = "sample restful webservice returning JSON output")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/good/greet/{name}")
  public Response greet(@PathParam("name") final String name) {
    return Response.status(200).entity(new Greeting(name)).build();
  }

  @Counter
  @Operation(
      summary = "test injector",
      description = "sample restful webservice returning JSON output")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/good/testInjector/{name}")
  public Response testInjector(@PathParam("name") final String name) {
    MeterRegistry meterRegistry = injector.getInstance(MeterRegistry.class);

    for (Meter meter : meterRegistry.getMeters()) {}

    return Response.status(200)
        .entity(new Greeting(name + meterRegistry.getClass().getName()))
        .build();
  }
}
