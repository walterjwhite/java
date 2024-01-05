package com.walterjwhite.logging.util;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArgumentUtilTest {
  @Test
  public void testNoArgs() {
    final Object[] output = ArgumentUtil.getArguments(false, null, 3);
    Assertions.assertNull(output);
  }

  @Test
  public void test3StringArgs() {
    final String[] input = new String[] {"one", "two", "three"};
    final Object[] output = ArgumentUtil.getArguments(false, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals(3, output.length);
  }

  @Test
  public void test1StringArg() {
    final String[] input = new String[] {"one"};
    final Object[] output = ArgumentUtil.getArguments(false, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals(1, output.length);
  }

  @Test
  public void test1ObjectArg() {
    final Object[] input = new Object[] {String.class};
    final Object[] output = ArgumentUtil.getArguments(false, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals(1, output.length);
  }

  @Test
  public void test1CollectionArg() {
    final List input = Arrays.asList("one");
    final Object[] output = ArgumentUtil.getArguments(false, new Object[] {input}, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals(1, output.length);
  }

  @Test
  public void test1ArgNonArray() {
    final String input = "one";
    final Object output = ArgumentUtil.getArgument(false, input, 3);
    Assertions.assertNotNull(output);
    Assertions.assertEquals(input, output);
  }
}
