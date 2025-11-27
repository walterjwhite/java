package com.walterjwhite.examples.cli.commentremover;

import com.walterjwhite.inject.cli.property.OperatingMode;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.property.api.annotation.DefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentRemoverOperatingMode implements OperatingMode {
  @DefaultValue
  Default(CommentRemoverCommandLineHandler.class);

  private final Class<? extends CommandLineHandler> initiatorClass;
}
