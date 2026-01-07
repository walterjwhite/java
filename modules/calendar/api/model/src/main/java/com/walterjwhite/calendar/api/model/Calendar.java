package com.walterjwhite.calendar.api.model;

import com.walterjwhite.datastore.jdo.model.AbstractNamedEntity;
import com.walterjwhite.person.api.model.Person;
import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@PersistenceCapable
public class Calendar extends AbstractNamedEntity {
  protected Person owner;
}
