package com.walterjwhite.identity.api.model.account;

import com.walterjwhite.datastore.api.model.entity.AbstractUUIDEntity;
import com.walterjwhite.identity.api.model.Principal;
import java.util.HashMap;
import java.util.Map;
import javax.jdo.annotations.PersistenceCapable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(doNotUseGetters = true)
@PersistenceCapable
public class GuestAccount extends AbstractUUIDEntity implements Principal {
  @EqualsAndHashCode.Exclude protected Map<String, String> attributes = new HashMap<>();

  @Override
  public String getPrincipalId() {
    return null;
  }
}
