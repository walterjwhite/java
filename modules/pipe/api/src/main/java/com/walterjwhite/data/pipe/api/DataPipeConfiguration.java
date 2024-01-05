package com.walterjwhite.data.pipe.api;

import com.walterjwhite.data.pipe.api.filter.AbstractFilterConfiguration;
import com.walterjwhite.data.pipe.api.sink.AbstractSourceConfiguration;
import com.walterjwhite.data.pipe.api.source.AbstractSinkConfiguration;
import com.walterjwhite.data.pipe.api.transformation.AbstractTransformationConfiguration;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@NoArgsConstructor
public class DataPipeConfiguration implements Serializable {
  protected Set<AbstractSourceConfiguration> sourceConfigurations = new HashSet<>();
  protected Set<AbstractSinkConfiguration> sinkConfigurations = new HashSet<>();
  protected List<AbstractTransformationConfiguration> transformationConfigurations =
      new ArrayList<>();
  protected Set<AbstractFilterConfiguration> filterConfigurations = new HashSet<>();

  protected Set<String> libraryPaths;
}
