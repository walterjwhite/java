package com.walterjwhite.transform.key_value;

import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeyValueResultConfiguration extends TransformInstanceConfiguration
    implements Serializable {

  @EqualsAndHashCode.Exclude protected String result;
  @EqualsAndHashCode.Exclude protected String resultClass;
}
