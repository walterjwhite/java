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
  // argument index -> value (always replace argument documentId with value 12345)
  @EqualsAndHashCode.Exclude protected Map<Integer, String> argumentMap;

  // we could merely refactor our code to call another method so that we can transform the result of
  // that method which would be the original parameter
  // however, if we have code that we are unable to modify, it would be useful to be able to do so
}
