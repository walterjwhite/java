package com.walterjwhite.serialization.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.inject.Provider;

public abstract class AbstractObjectMapperProvider implements Provider<ObjectMapper> {
  protected final ObjectMapper objectMapper;
  protected final JavaTimeModule javaTimeModule = new JavaTimeModule();

  public AbstractObjectMapperProvider() {
    objectMapper = getInstance();

    // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
    javaTimeModule.addDeserializer(
        LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
    objectMapper.registerModule(javaTimeModule);
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator());
    // ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
    //        objectMapper.configure()

    // ignore id / version fields
    //    mapper.setDefaultVisibility(
    //        JsonAutoDetect.Value.construct(
    //            PropertyAccessor.ALL, JsonAutoDetect.Visibility.NON_PRIVATE));
  }

  protected abstract ObjectMapper getInstance();

  @Override
  public ObjectMapper get() {
    return objectMapper;
  }
}
