package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.enumeration.AlertReason;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true, callSuper = true)
@Data
public class AlertMessage extends Message {
  protected AlertReason alertReason;
}
