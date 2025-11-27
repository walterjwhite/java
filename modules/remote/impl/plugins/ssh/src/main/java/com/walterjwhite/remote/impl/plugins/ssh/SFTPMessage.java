package com.walterjwhite.remote.impl.plugins.ssh;

import com.walterjwhite.remote.api.model.message.Message;
import com.walterjwhite.ssh.api.model.sftp.SFTPTransfer;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class SFTPMessage extends Message {
  protected SFTPTransfer transfer;
}
