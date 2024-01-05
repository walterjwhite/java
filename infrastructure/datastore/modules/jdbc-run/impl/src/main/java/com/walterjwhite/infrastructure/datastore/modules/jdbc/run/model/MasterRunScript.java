package com.walterjwhite.infrastructure.datastore.modules.jdbc.run.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
public class MasterRunScript implements Serializable {
  protected List<String> scriptFilenames = new ArrayList<>();
}
