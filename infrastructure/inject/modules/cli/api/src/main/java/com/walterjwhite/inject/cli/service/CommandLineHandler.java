package com.walterjwhite.inject.cli.service;

/*
 * TODO: invoke all shutdown aware hooks (on shutdown)
 */
public interface CommandLineHandler {


  void run(final String... arguments) throws Exception;
}
