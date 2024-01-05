package com.walterjwhite.transform.hash;

import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class HashResultConfiguration extends TransformInstanceConfiguration
    implements Serializable {
  protected String hashAlgorithm;
}
