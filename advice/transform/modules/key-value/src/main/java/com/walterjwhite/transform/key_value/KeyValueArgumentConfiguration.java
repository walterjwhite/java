package com.walterjwhite.transform.key_value;

import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import java.io.Serializable;
import java.util.Map;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeyValueArgumentConfiguration extends TransformInstanceConfiguration
    implements Serializable {
  @EqualsAndHashCode.Exclude protected Map<Integer, String> argumentMap;

}
