package com.walterjwhite.examples.rest.ipify;

import com.walterjwhite.examples.rest.RestClient;
import com.walterjwhite.examples.rest.WebTargetResponse;
import com.walterjwhite.examples.rest.builder.IpIfyRequestBuilder;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import java.util.Map;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(onConstructor_ = @Inject)
public class IpIfyRestClientExampleCommandLineHandler implements CommandLineHandler {

  protected final RestClient restClient = new RestClient();


  @Override
  public void run(String... arguments) {
    final WebTargetResponse<Map> ipifyResponse = restClient.call(IpIfyRequestBuilder.build());

    LOGGER.debug("Response Status: {}", ipifyResponse.getResponse().getStatus());
    LOGGER.debug("Response Data: {}", ipifyResponse.getRawResponse());
  }
}
