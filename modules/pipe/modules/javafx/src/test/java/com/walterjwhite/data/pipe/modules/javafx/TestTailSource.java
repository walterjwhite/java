package com.walterjwhite.data.pipe.modules.javafx;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class TestTailSource {

  @Test
  public void test() throws IOException {
    TailSource tailSource = new TailSource();
    tailSource.write("hi\n".getBytes());

    Assert.assertTrue(tailSource.iterator().hasNext());
    final String read = tailSource.iterator().next();
    System.out.println("read:" + read);
    Assert.assertEquals("hi", read);
  }
}
