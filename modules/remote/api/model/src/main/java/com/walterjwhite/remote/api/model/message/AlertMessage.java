package com.walterjwhite.remote.api.model.message;

import com.walterjwhite.remote.api.enumeration.AlertReason;
import com.walterjwhite.remote.api.model.Client;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;


@ToString(doNotUseGetters = true, callSuper = true)
@Data
public class AlertMessage extends Message {
  protected AlertReason alertReason;
}
