package com.walterjwhite.remote.impl.plugins.heartbeat;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.shell.api.model.ping.PingRequest;
import com.walterjwhite.shell.api.model.ping.PingResponse;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
@AllArgsConstructor
@NoArgsConstructor
public class PingMessageResponse extends Message {
  protected PingRequest pingRequest;

  protected PingResponse pingResponse;
}
