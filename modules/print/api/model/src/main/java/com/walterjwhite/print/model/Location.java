package com.walterjwhite.print.model;

import javax.jdo.annotations.PersistenceCapable;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class Location {
  protected String name;
  protected String description;

  protected String address;
  protected String floor;
  protected String room;
  protected String zipCode;
  protected String state;
  protected String country;
}
