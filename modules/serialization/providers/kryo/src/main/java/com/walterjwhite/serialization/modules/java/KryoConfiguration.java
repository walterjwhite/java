package com.walterjwhite.serialization.modules.java;

import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;

@Getter
public class KryoConfiguration {
  protected final Set<Class> serializationClasses = new LinkedHashSet<>();
}
