package com.walterjwhite.datastore.jdo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
@PersistenceCapable
public class Tag extends AbstractNamedEntity {
  @Column protected Tag parent;
  @Persistent protected Set<Tag> children;

  protected List<EntityReference> entityReferences = new ArrayList();

  public Tag(String name, Tag parent) {
    super(name);
    this.parent = parent;
  }

  public Tag(String name) {
    super(name);
  }
}
