package com.walterjwhite.transform.rot13_5;

import com.walterjwhite.transform.ResultTransformer;
import com.walterjwhite.transform.annotation.Transformer;
import com.walterjwhite.transform.configuration.TransformInstanceConfiguration;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bytecode.assign.Assigner;

// for greater flexibility, this should simply be a Function<Input, Output>
// this use-case is where the input and output types are the same
@Transformer(
    configurationClass = TransformInstanceConfiguration.class,
    pattern = "transform/rot13_5/result")
public class ROT13_5ResultAdvice {
  @Advice.OnMethodExit
  public static void intercept(
      @Advice.Return(readOnly = false, typing = Assigner.Typing.DYNAMIC) Object value) {
    value = ResultTransformer.transform(value, ROT13_5ResultAdvice::transform);
  }

  public static Object transform(final Object input) {
    if (input == null) {
      return null;
    }

    if (!String.class.equals(input.getClass())) {
      return input;
    }

    return ROT13_5Util.transform((String) input);
  }
}
