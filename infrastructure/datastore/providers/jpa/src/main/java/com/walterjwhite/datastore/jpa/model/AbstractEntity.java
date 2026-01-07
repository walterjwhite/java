package com.walterjwhite.datastore.jpa.model;

import com.walterjwhite.datastore.api.model.Entity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.ToString;

@Embeddable
@MappedSuperclass
@Data
@ToString(doNotUseGetters = true)
public abstract class AbstractEntity implements Entity<Integer> {
  @Id @GeneratedValue protected Integer id;
}
