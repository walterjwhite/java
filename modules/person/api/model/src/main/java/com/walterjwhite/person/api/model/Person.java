package com.walterjwhite.person.api.model;

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
public class Person {

  protected String firstName;

  protected String middleName;

  protected String lastName;


  protected LocalDate birthDate;

}
