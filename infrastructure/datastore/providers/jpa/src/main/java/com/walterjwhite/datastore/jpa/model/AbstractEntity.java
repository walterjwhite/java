package com.walterjwhite.datastore.jpa.model;

import com.walterjwhite.datastore.api.model.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.Id;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractEntity implements Entity<Integer> {

  @Id
  @GeneratedValue
  protected Integer id;
}
