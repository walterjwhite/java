package com.walterjwhite.shell.api.model.dig;

import com.walterjwhite.shell.api.model.IPAddress;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class DigResult /*<DigRequest>*/ {
  protected DigRequest digRequest;

  protected List</*DigRequestIPAddress*/ IPAddress> digRequestIPAddresses;

  public DigResult(DigRequest digRequest, List<IPAddress> digRequestIPAddresses) {
    this();
    this.digRequest = digRequest;
    if (digRequestIPAddresses != null && !digRequestIPAddresses.isEmpty()) {
      this.digRequestIPAddresses.addAll(digRequestIPAddresses);
    }
  }

  public DigResult() {
    this.digRequestIPAddresses = new ArrayList<>();
  }

}
