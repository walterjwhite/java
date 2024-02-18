package com.walterjwhite.property.api.property;

import com.walterjwhite.logging.annotation.Sensitive;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class Secret {
  protected String key;

  @ToString.Exclude
  @Sensitive
  @Getter(onMethod_ = @Sensitive)
  @Setter(onMethod_ = @Sensitive)
  protected transient String value;
}
