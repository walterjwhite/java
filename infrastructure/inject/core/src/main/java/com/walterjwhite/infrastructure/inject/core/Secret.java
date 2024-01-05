package com.walterjwhite.infrastructure.inject.core;

import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.logging.annotation.Sensitive;
import com.walterjwhite.property.api.SecretService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString(doNotUseGetters = true)
@Data
public class Secret {
  private static transient SecretService SECRET_SERVICE;

  protected String key;

  @Getter(onMethod = @__(@Sensitive))
  @Setter(onMethod = @__(@Sensitive))
  @ToString.Exclude
  @Sensitive
  protected transient String value;

  protected void init() {
    if (SECRET_SERVICE == null) {
      SECRET_SERVICE =
          ApplicationHelper.getApplicationInstance().getInjector().getInstance(SecretService.class);
    }
  }

  public String getValue() {
    if (value != null) {
      return value;
    }

    if (key == null) {
      throw new IllegalArgumentException("Key must not be null.");
    }

    init();
    value = SECRET_SERVICE.get(key);
    return value;
  }
}
