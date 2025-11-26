package com.walterjwhite.email.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractNamedEntity;
import java.util.HashMap;
import java.util.Map;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class EmailProvider extends AbstractNamedEntity {
  protected Map<String, String> settings = new HashMap<>();

  public EmailProvider(String name, String description, Map<String, String> settings) {
    super(name, description);

    if (settings != null && !settings.isEmpty()) {
      this.settings.putAll(settings);
    }
  }
}
