package com.walterjwhite.email.api.service;

import com.walterjwhite.email.api.model.Email;

public interface EmailSendService {
  void send(Email email) throws Exception;
}
