package com.walterjwhite.modules.web.service.sample;



import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/sample")
public class SampleWebService {
  
  @GET
  public Response getJson() {
    return Response.status(200).entity("live").build();
  }
}
