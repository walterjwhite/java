package com.walterjwhite.property.validation;

import lombok.Getter;

@Getter
public class UsedAttribute extends Attribute {
  protected final String className;
  protected final String methodName;

  public UsedAttribute(String name, String typeName, String className, String methodName) {
    super(name, typeName);
    this.className = className;
    this.methodName = methodName;
  }
}
