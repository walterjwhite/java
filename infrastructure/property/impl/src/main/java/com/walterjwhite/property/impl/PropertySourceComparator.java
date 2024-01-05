package com.walterjwhite.property.impl;

import com.walterjwhite.property.api.annotation.PropertySourceIndex;
import com.walterjwhite.property.api.source.PropertySource;
import java.util.Comparator;

public class PropertySourceComparator implements Comparator<Class<? extends PropertySource>> {
  @Override
  public int compare(Class<? extends PropertySource> left, Class<? extends PropertySource> right) {
    final PropertySourceIndex leftIndex = left.getAnnotation(PropertySourceIndex.class);
    final PropertySourceIndex rightIndex = right.getAnnotation(PropertySourceIndex.class);

    if (leftIndex == null) {
      if (rightIndex == null) {
        return 0;
      }

      return -rightIndex.value();
    }

    if (rightIndex == null) {
      return leftIndex.value();
    }

    return leftIndex.value() - rightIndex.value();
  }

  @Override
  public Comparator<Class<? extends PropertySource>> reversed() {
    return null;
  }
}
