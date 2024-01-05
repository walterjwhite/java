package com.walterjwhite.transform;

import java.util.Map;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransformedType {
  String(String.class) {
    public Object transform(final Object input, final Function<Object, Object> function) {
      return function.apply(input);
    }
  },
  Array(Object[].class) {
    public Object transform(final Object input, final Function<Object, Object> function) {
      final Object[] sInput = ((Object[]) input);
      for (int i = 0; i < sInput.length; i++) {
        try {
          sInput[i] = TransformedType.get(sInput[i]).transform(sInput[i], function);
        } catch (IllegalArgumentException e) {
          // unsupported
        }
      }

      return sInput;
    }
  },
  JavaMap(Map.class) {
    public Object transform(final Object input, final Function<Object, Object> function) {
      // need some information on which keys to transform
      return input;
    }
  },
  ;

  final Class supportedType;

  public abstract Object transform(final Object input, final Function<Object, Object> function);

  public static TransformedType get(final Object input) {
    if (input == null) {
      return null;
    }

    for (final TransformedType transformedType : values()) {
      if (transformedType.supportedType.equals(input.getClass())) {
        return transformedType;
      }
    }

    throw new IllegalArgumentException(input.getClass() + " is not supported");
  }
}
