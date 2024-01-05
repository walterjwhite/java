package com.walterjwhite.logging.enumeration;

import com.walterjwhite.logging.formatter.CollectionFormatter;
import com.walterjwhite.logging.formatter.MapFormatter;
import com.walterjwhite.logging.util.ArgumentUtil;
import java.util.Collection;
import java.util.Map;

public enum ArgumentType {
  /*
  ByteArrayArgument(ByteArrayFormatter.class, byte[].class),
  IntArrayArgument(IntArrayFormatter.class, int[].class),
  DoubleArrayArgument(DoubleArrayFormatter.class, double[].class),
  FloatArrayArgument(FloatArrayFormatter.class, float[].class),
  CharArrayArgument(CharArrayFormatter.class, char[].class),
  LongArrayArgument(LongArrayFormatter.class, long[].class),
  ShortArrayArgument(ShortArrayFormatter.class, short[].class),
  BooleanArrayArgument(BooleanArrayFormatter.class, boolean[].class),
   */
  CollectionArgument(Collection.class) {
    public Object doFormat(
        final boolean isSensitive, final Object input, final int argumentLength) {
      return CollectionFormatter.format(isSensitive, (Collection) input, argumentLength);
    }

    protected int length(final Object input) {
      return ((Collection) input).size();
    }
  },
  MapArgument(Map.class) {
    @Override
    protected int length(Object input) {
      return ((Map) input).size();
    }

    public Object doFormat(
        final boolean isSensitive, final Object input, final int argumentLength) {
      return MapFormatter.format(isSensitive, (Map) input, argumentLength);
    }
  },
  ObjectArrayArgument(Object[].class) {
    @Override
    protected int length(Object input) {
      return ((Object[]) input).length;
    }

    public Object doFormat(
        final boolean isSensitive, final Object input, final int argumentLength) {
      final Object[] inputArray = (Object[]) input;
      final String[] out = new String[argumentLength];
      for (int i = 0; i < argumentLength; i++) {
        out[i] = ArgumentUtil.format(inputArray[i], isSensitive);
      }
      // return ArgumentUtil.getArguments(isSensitive, ((Object[]) input), argumentLength);

      return out;
    }
  },
  ObjectArgument(Object.class) {
    @Override
    protected int length(Object input) {
      return 1;
    }

    public Object doFormat(
        final boolean isSensitive, final Object input, final int argumentLength) {
      return ArgumentUtil.format(input, isSensitive);
    }
  };

  private final Class argumentType;

  ArgumentType(Class argumentType) {
    this.argumentType = argumentType;
  }

  public boolean supports(final Class argumentClass) {
    return argumentType.isAssignableFrom(argumentClass);
  }

  public Object format(final boolean isSensitive, final Object input, final int numberOfArguments) {
    final int inputLength = length(input);
    final int argumentLength = length(numberOfArguments, inputLength);
    final boolean hasMore = hasMore(numberOfArguments, inputLength);

    final Object out = doFormat(isSensitive, input, argumentLength);
    if (!hasMore) {
      return out;
    }

    return ArgumentUtil.formatOutput((String[]) out, true);
  }

  protected boolean hasMore(final int numberOfArguments, final int inputLength) {
    return inputLength > numberOfArguments;
  }

  protected abstract Object doFormat(
      final boolean isSensitive, final Object input, final int argumentLength);

  protected int length(final int numberOfArguments, final int inputLength) {
    if (inputLength >= numberOfArguments) {
      return numberOfArguments;
    }

    return inputLength;
  }

  protected abstract int length(final Object input);
}
