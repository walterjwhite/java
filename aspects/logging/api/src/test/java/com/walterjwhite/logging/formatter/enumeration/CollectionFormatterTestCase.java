package com.walterjwhite.logging.formatter.enumeration;

import java.util.Arrays;
import java.util.Collection;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;

// each enum value represents a test-case
@Getter
@RequiredArgsConstructor
public enum CollectionFormatterTestCase implements TestCase {
  NullInput(null, 3, false) {
    public void validate(final Object result) {
      Assertions.assertNotNull(result);
    }
  },
  OneArgumentInput(Arrays.asList("one"), 3, false) {
    public void validate(final Object result) {
      Assertions.assertNotNull(result);
      Assertions.assertEquals("one", result);
    }
  },
  TwoArgumentsInput(Arrays.asList("one", "two"), 3, false) {
    public void validate(final Object result) {
      Assertions.assertNotNull(result);
      Assertions.assertEquals("(one,two)", result);
    }
  },
  ThreeArgumentsInput(Arrays.asList("one", "two", "three"), 3, false) {
    public void validate(final Object result) {
      Assertions.assertNotNull(result);
      Assertions.assertEquals("(one,two,three)", result);
    }
  },
  FourArgumentsInput(Arrays.asList("one", "two", "three", "four"), 3, false) {
    public void validate(final Object result) {
      Assertions.assertNotNull(result);
      Assertions.assertEquals("first 3 (one,two,three)", result);
    }
  };

  private final Collection input;
  private final int numberOfArguments;
  private final boolean sensitive;
  // private static final Method collectionFormatterMethod = CollectionFormatter::format;
}
