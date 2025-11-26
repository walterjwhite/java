package com.walterjwhite.datastore.jpa.model;

import com.walterjwhite.datastore.api.model.Entity;
import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


@Embeddable
@MappedSuperclass
@Data
public abstract class AbstractUUIDEntity implements Entity<String> {
  protected String id;

}
