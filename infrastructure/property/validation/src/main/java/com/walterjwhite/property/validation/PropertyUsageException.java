package com.walterjwhite.property.validation;

public class PropertyUsageException extends IllegalStateException {
  protected final Attribute declaredAttribute;
  protected final UsedAttribute usedAttribute;

  public PropertyUsageException(Attribute declaredAttribute, UsedAttribute usedAttribute) {
    super(
        formatErrorMessage(
            declaredAttribute.getName(),
            declaredAttribute.getTypeName(),
            usedAttribute.getName(),
            usedAttribute.getTypeName(),
            usedAttribute.getClassName(),
            usedAttribute.getMethodName()));
    this.declaredAttribute = declaredAttribute;
    this.usedAttribute = usedAttribute;
  }

  private static final String ERROR_MESSAGE_FORMAT =
      "Property (%s) (%s) used as (%s) (%s) in (%s:%s)";

  private static String formatErrorMessage(
      final String declaredAttributeName,
      final String declaredAttributeTypeName,
      final String usedAttributeName,
      final String usedAttributeType,
      final String usedAttributeClass,
      final String usedAttributeMethod) {
    return String.format(
        ERROR_MESSAGE_FORMAT,
        declaredAttributeName,
        declaredAttributeTypeName,
        usedAttributeName,
        usedAttributeType,
        usedAttributeClass,
        usedAttributeMethod);
  }
}
