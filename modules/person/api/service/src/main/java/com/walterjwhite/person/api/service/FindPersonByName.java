package com.walterjwhite.person.api.service;

import com.walterjwhite.datastore.query.AbstractQuery;
import com.walterjwhite.person.api.model.Person;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class FindPersonByName extends AbstractQuery<Person, Person> {
  protected final String firstName;
  protected final String middleName;
  protected final String lastName;

  public FindPersonByName(
      int offset, int recordCount, String firstName, String middleName, String lastName) {
    super(offset, recordCount, Person.class, Person.class, false);
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
  }

  //    Predicate firstNameCondition =
  //            criteriaBuilder.like(
  //                    messageCriteriaQueryConfiguration.getRoot().get(Person_.firstName), name);
  //
  //    Predicate lastNameCondition =
  //            criteriaBuilder.like(
  //                    messageCriteriaQueryConfiguration.getRoot().get(Person_.lastName), name);
  //
  //    Predicate middleNameCondition =
  //            criteriaBuilder.like(
  //                    messageCriteriaQueryConfiguration.getRoot().get(Person_.middleName), name);
}
