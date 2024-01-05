package com.walterjwhite.serialization.jackson.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walterjwhite.serialization.jackson.AbstractObjectMapperProvider;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor_ = @Inject)
public class ObjectMapperProvider extends AbstractObjectMapperProvider
    implements Provider<ObjectMapper> {
  protected ObjectMapper getInstance() {
    return new ObjectMapper();
  }
}
