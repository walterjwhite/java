package com.walterjwhite.datastore.api.annotation;

import com.walterjwhite.datastore.api.repository.QueryConfiguration;
import java.lang.annotation.*;

/** Registers a QueryBuilder for a given Query Configuration */
@Target({
  ElementType.METHOD,
  ElementType.FIELD,
  ElementType.LOCAL_VARIABLE,
  ElementType.PARAMETER,
  ElementType.TYPE_USE,
  ElementType.TYPE_PARAMETER,
  ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Supports {
  Class<? extends QueryConfiguration> value();
}
