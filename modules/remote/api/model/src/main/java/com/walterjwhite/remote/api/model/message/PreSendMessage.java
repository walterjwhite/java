package com.walterjwhite.remote.api.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PreSendMessage {
  protected final Message message;
}
