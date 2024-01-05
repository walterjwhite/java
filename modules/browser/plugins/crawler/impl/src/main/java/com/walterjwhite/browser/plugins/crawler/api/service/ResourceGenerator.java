package com.walterjwhite.browser.plugins.crawler.api.service;

/**
 * Unused, instead, I'm observing events in guava, need to make certain those events are synchronous
 * and not async
 */
public interface ResourceGenerator {

  boolean isPostProcess(final String uri);
}
