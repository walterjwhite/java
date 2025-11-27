package com.walterjwhite.datastore.jpa.model;

import jakarta.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true, callSuper = true)
@NoArgsConstructor
public class Tag extends AbstractNamedEntity {
  @Column protected Tag parent;
  protected Set<Tag> children;

  protected List<EntityReference> entityReferences = new ArrayList();

  public Tag(String name, Tag parent) {
    super(name);
    this.parent = parent;
  }

  public Tag(String name) {
    super(name);
  }
}
