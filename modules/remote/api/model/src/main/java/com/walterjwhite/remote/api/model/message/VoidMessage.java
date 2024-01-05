package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.model.Client;

public class VoidMessage extends Message {
  private VoidMessage() {
    super((Client) null, -1);
  }
}
