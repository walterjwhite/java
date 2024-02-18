package com.walterjwhite.data.pipe.modules.email.javamail;

import com.google.inject.AbstractModule;
import com.walterjwhite.data.pipe.modules.email.javamail.sink.EmailJPASink;



public class JavaMailEmailPipeModule extends AbstractModule {
  @Override
  protected void configure() {

    bind(EmailJPASink.class);
    bind(JavaMailEmailReaderPipe.class);
  }
}
