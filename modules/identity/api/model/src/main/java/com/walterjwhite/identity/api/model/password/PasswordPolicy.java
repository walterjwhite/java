package com.walterjwhite.identity.api.model.password;

import com.walterjwhite.datastore.jdo.model.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true)
@Data
@PersistenceCapable
public class PasswordPolicy extends AbstractNamedEntity {
  protected int minimumLength;
  protected int maximumLength;
  protected CharacterSetConfiguration[] characterSetConfigurations;
}
