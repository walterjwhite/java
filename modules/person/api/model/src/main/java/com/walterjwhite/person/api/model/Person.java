package com.walterjwhite.person.api.model;

import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.time.LocalDate;
import javax.jdo.annotations.PersistenceCapable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
public class Person extends AbstractEntity {

  protected String firstName;

  protected String middleName;

  protected String lastName;

  // @Encrypted

  protected LocalDate birthDate;

  // protected Set<EmailAccount> emailAccounts;
}
