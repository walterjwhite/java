package com.walterjwhite.email.api.service.selector.concept;

public interface ProviderTypeMap<ArgumentType> {
  ProviderType getProviderType(ArgumentType argument);
}
