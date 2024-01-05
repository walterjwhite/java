package com.walterjwhite.remote.api.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** Used for event handling. */
@Getter
@AllArgsConstructor
public class PreSendMessage {
  protected final Message message;
}
