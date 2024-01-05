package com.walterjwhite.examples.cli.jersey;

import com.walterjwhite.inject.cli.service.DaemonCommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(onConstructor_ = @Inject)
public class JerseyGuiceCLIExampleCommandLineHandler implements DaemonCommandLineHandler {}
