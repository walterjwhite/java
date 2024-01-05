package com.walterjwhite.remote.api.service;

import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.model.message.Message;

/** Wrapper around external queue service */
public interface MessageWriterService {
  void write(Message message, Client... clients) throws Exception;
}
