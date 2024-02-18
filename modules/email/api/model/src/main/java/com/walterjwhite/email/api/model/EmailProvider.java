package com.walterjwhite.email.api.model;


import com.walterjwhite.datastore.jdo.model.AbstractNamedEntity;
import com.walterjwhite.email.api.enumeration.EmailProviderType;
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

  protected EmailProviderType type;


  protected Map<String, String> settings = new HashMap<>();

  public EmailProvider(
      String name, String description, EmailProviderType type, Map<String, String> settings) {
    super(name, description);
    this.type = type;

    if (settings != null && !settings.isEmpty()) {
      this.settings.putAll(settings);
    }
  }
}
