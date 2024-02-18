package com.walterjwhite.infrastructure.datastore.modules.jdbc.run.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(doNotUseGetters = true)
public class MasterRunScript implements Serializable {
    protected List<String> scriptFilenames = new ArrayList<>();
}
