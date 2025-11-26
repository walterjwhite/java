package com.walterjwhite.serialization.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import jakarta.inject.Provider;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractObjectMapperProvider implements Provider<ObjectMapper> {
  protected final ObjectMapper objectMapper;
  protected final JavaTimeModule javaTimeModule = new JavaTimeModule();

  public AbstractObjectMapperProvider() {
    objectMapper = getInstance();

    javaTimeModule.addDeserializer(
        LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
    objectMapper.registerModule(javaTimeModule);
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator());

  }

  protected abstract ObjectMapper getInstance();

  @Override
  public ObjectMapper get() {
    return objectMapper;
  }
}
