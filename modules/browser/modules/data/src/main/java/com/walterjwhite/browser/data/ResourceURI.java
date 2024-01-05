package com.walterjwhite.browser.data;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class ResourceURI extends AbstractNamedEntity {
  @ToString.Exclude protected Set<BrowserSessionResourceURI> browserSessionResourceURISES;

  public ResourceURI(String uri) {
    super(uri);
  }

  public ResourceURI() {

    browserSessionResourceURISES = new HashSet<>();
  }
}
