package com.walterjwhite.ssh.api.model;

import com.walterjwhite.ssh.api.model.command.SSHCommand;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@NoArgsConstructor
@PersistenceCapable
public class SSHHost {
  protected String hostname;
  protected int port;

  protected String hostkey;

  protected List<SSHCommand> commands = new ArrayList<>();

  public SSHHost(String hostname, int port, String hostkey) {
    this.hostname = hostname;
    this.port = port;
    this.hostkey = hostkey;
  }

  public SSHHost(String hostname) {
    this.hostname = hostname;
  }
}
