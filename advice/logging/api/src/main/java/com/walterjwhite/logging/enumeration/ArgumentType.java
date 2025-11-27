package com.walterjwhite.logging.enumeration;

import com.walterjwhite.logging.util.ArgumentUtil;
import com.walterjwhite.logging.util.ObjectFormatUtil;
import java.util.Collection;
import java.util.Iterator;
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
    public String doFormat(
        final int lastCharactersToDisplay, final Object input, final int argumentLength) {
      final String[] out = new String[argumentLength];
      final Iterator<Object> iterator = ((Collection) input).iterator();

      for (int i = 0; i < argumentLength; i++) {
        out[i] = ObjectFormatUtil.format(iterator.next(), lastCharactersToDisplay);
      }

      return ObjectFormatUtil.format(argumentLength, ((Collection) input).size(), out);
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

    public String doFormat(
        final int lastCharactersToDisplay, final Object input, final int argumentLength) {
      final String[] out = new String[argumentLength];
      final Iterator<Object> iterator = ((Map) input).entrySet().iterator();

      for (int i = 0; i < argumentLength; i++) {
        out[i] = ObjectFormatUtil.format(iterator.next(), lastCharactersToDisplay);
      }

      return ObjectFormatUtil.format(argumentLength, ((Map) input).size(), out);
    }
  },
  ObjectArrayArgument(Object[].class) {
    @Override
    protected int length(Object input) {
      return ((Object[]) input).length;
    }

    public String doFormat(
        final int lastCharactersToDisplay, final Object input, final int argumentLength) {
      final Object[] inputArray = (Object[]) input;
      final String[] out = new String[argumentLength];
      for (int i = 0; i < argumentLength && i < inputArray.length; i++) {
        out[i] = ObjectFormatUtil.format(inputArray[i], lastCharactersToDisplay);
      }

      return ObjectFormatUtil.format(argumentLength, inputArray.length, out);
    }
  },
  ObjectArgument(Object.class) {
    @Override
    protected int length(Object input) {
      return 1;
    }

    public String doFormat(
        final int lastCharactersToDisplay, final Object input, final int argumentLength) {
      return ObjectFormatUtil.format(input, lastCharactersToDisplay);
    }
  };

  private final Class argumentType;

  ArgumentType(Class argumentType) {
    this.argumentType = argumentType;
  }

  public boolean supports(final Class argumentClass) {
    return argumentType.isAssignableFrom(argumentClass);
  }

  public String format(
      final int lastCharactersToDisplay, final Object input, final int numberOfArguments) {
    final int inputLength = length(input);
    final int argumentLength = ArgumentUtil.length(numberOfArguments, inputLength);

    return doFormat(lastCharactersToDisplay, input, argumentLength);
  }

  protected abstract String doFormat(
      final int lastCharactersToDisplay, final Object input, final int argumentLength);

  protected abstract int length(final Object input);
}
