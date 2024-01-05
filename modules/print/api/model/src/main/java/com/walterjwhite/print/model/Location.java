package com.walterjwhite.print.model;

import com.walterjwhite.datastore.api.model.entity.AbstractNamedEntity;
import javax.jdo.annotations.PersistenceCapable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** TODO: migrate this "location" stuff to another project and re-use it here. */
@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true, callSuper = true)
@PersistenceCapable
public class Location extends AbstractNamedEntity {
  protected String address;
  protected String floor;
  protected String room;
  protected String zipCode;
  protected String state;
  protected String country;

  public Location(
      String name,
      String address,
      String floor,
      String room,
      String zipCode,
      String state,
      String country) {
    super(name);

    this.address = address;
    this.floor = floor;
    this.room = room;
    this.zipCode = zipCode;
    this.state = state;
    this.country = country;
  }
}
