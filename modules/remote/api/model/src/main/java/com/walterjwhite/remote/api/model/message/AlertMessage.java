package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.enumeration.AlertReason;
import com.walterjwhite.remote.api.model.Client;
import lombok.Data;
import lombok.ToString;

/** Send an alert indicating this node may be compromised. */
@ToString(doNotUseGetters = true, callSuper = true)
@Data
public class AlertMessage extends Message {

  protected AlertReason alertReason;

  public AlertMessage() {
    super((Client) null, -1);
  }
}
