package com.walterjwhite.datastore.jdo.model;

import java.io.Serializable;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Index;
import javax.jdo.annotations.Indices;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
@PersistenceCapable
@Indices(
    @Index(
        columns = {@Column(name = "entityType"), @Column(name = "entityId")},
        unique = "true"))
public class EntityReference extends AbstractEntity {
  @Persistent protected EntityType entityType;

  @Persistent protected Serializable entityId;
}
