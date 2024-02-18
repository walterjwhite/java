package com.walterjwhite.datastore.jpa.model;

import com.walterjwhite.datastore.api.model.Entity;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.Embeddable;
import jakarta.persistence.MappedSuperclass;

@Embeddable
@MappedSuperclass
@Data
public abstract class AbstractUUIDEntity implements Entity<String> {

  protected String id;


}
