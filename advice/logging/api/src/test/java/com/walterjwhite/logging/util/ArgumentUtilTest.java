package com.walterjwhite.logging.util;

import com.walterjwhite.logging.annotation.Sensitive;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArgumentUtilTest {
  @Test
  public void null_input() {
    final Sensitive[] sensitives = new Sensitive[1];
    sensitives[0] = null;
    final String output = ArgumentUtil.getArguments(sensitives, null, 3);
    Assertions.assertEquals("null", output);
  }

  @Test
  public void strings() {
    final String[] input = new String[] {"one", "two", "three"};
    final Sensitive[] sensitives = new Sensitive[3];
    sensitives[0] = null;
    sensitives[1] = null;
    sensitives[2] = null;
    final String output = ArgumentUtil.getArguments(sensitives, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals("[one, two, three]", output);
  }

  @Test
  public void strings_sensitive() {
    final String[] input = new String[] {"one-sensitive", "two", "three"};
    final Sensitive[] sensitives = new Sensitive[3];
    sensitives[0] = getInstanceOfAnnotation(4);
    sensitives[1] = null;
    sensitives[2] = null;
    final String output = ArgumentUtil.getArguments(sensitives, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals("[tive, two, three]", output);
  }

  @Test
  public void string() {
    final String[] input = new String[] {"one"};
    final Sensitive[] sensitives = new Sensitive[1];
    sensitives[0] = null;
    final String output = ArgumentUtil.getArguments(sensitives, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals("[one]", output);
  }

  @Test
  public void object() {
    final Object[] input = new Object[] {String.class};
    final Sensitive[] sensitives = new Sensitive[1];
    sensitives[0] = null;
    final String output = ArgumentUtil.getArguments(sensitives, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals("[class java.lang.String]", output);
  }

  @Test
  public void single_collection() {
    final List input = Arrays.asList("one", "two", "three");
    final Sensitive[] sensitives = new Sensitive[1];
    sensitives[0] = null;
    final String output = ArgumentUtil.getArguments(sensitives, new Object[] {input}, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals("[[one, two, three]]", output);
  }

  @Test
  public void map() {
    final Map<String, String> input = new LinkedHashMap<>();
    input.put("Harrisburg", "Pennsylvania");
    input.put("Dallas", "Texas");
    input.put("London", "England");

    final Sensitive[] sensitives = new Sensitive[1];
    sensitives[0] = null;
    final String output = ArgumentUtil.getArguments(sensitives, new Object[] {input}, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals("[[Harrisburg=Pennsylvania, Dallas=Texas, London=England]]", output);
  }

  @Test
  public void test1ArgNonArray() {
    final String input = "one";
    final String output = ArgumentUtil.toLoggableString(-1, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals(input, output);
  }

  @Test
  public void test1Arg_sensitive() {
    final String input = "sensitive";
    final String output = ArgumentUtil.toLoggableString(4, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals("tive", output);
  }

  Sensitive getInstanceOfAnnotation(final int value) {
    Sensitive annotation =
        new Sensitive() {
          @Override
          public int value() {
            return value;
          }

          @Override
          public Class<? extends Annotation> annotationType() {
            return Sensitive.class;
          }

          @Override
          public int hashCode() {
            return super.hashCode();
          }

          @Override
          public boolean equals(Object obj) {
            return super.equals(obj) && Sensitive.class.equals(obj.getClass());
          }
        };

    return annotation;
  }
}
