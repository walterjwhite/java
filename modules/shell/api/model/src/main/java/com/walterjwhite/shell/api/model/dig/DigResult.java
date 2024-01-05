package com.walterjwhite.shell.api.model.dig;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.shell.api.model.IPAddress;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class DigResult extends AbstractEntity /*<DigRequest>*/ {
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

  //    @Override
  //    public DigRequest getId() {
  //        return digRequest;
  //    }
  //    @Override
  //    public void setId(DigRequest id) {
  //        this.digRequest = id;
  //    }
}
