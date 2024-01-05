package com.walterjwhite.formatting;

import java.time.Duration;
import org.junit.Test;

public class DateTimeFormatterTest {
  @Test
  public void testBasics() {
    print(DateTimeFormatter.toString(Duration.ofHours(5).plusMinutes(43)));
    print(DateTimeFormatter.toString(Duration.ofHours(5).plusMinutes(43).plusSeconds(35)));
    print(DateTimeFormatter.toString(Duration.ofHours(5).plusMinutes(43).plusSeconds(35), 3));
    print(DateTimeFormatter.toString(Duration.ofHours(5).plusMinutes(43).plusSeconds(35), 4));
    print(DateTimeFormatter.toString(Duration.ofHours(1).plusMinutes(43).plusSeconds(35), 4));
    print(DateTimeFormatter.toString(Duration.ofHours(1).plusMinutes(2).plusSeconds(1), 4));
  }

  @Test
  public void stressTest() {
    long start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++)
      DateTimeFormatter.toString(Duration.ofHours(5).plusMinutes(43));
    long end = System.currentTimeMillis();
    print("runtime:" + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++)
      DateTimeFormatter.toString(Duration.ofHours(5).plusMinutes(43).plusSeconds(35));
    end = System.currentTimeMillis();
    print("runtime:" + (end - start));

    start = System.currentTimeMillis();
    for (int i = 0; i < 100000; i++)
      DateTimeFormatter.toString(Duration.ofHours(5).plusMinutes(43).plusSeconds(35), 3);
    end = System.currentTimeMillis();
    print("runtime:" + (end - start));
  }

  private static void print(String s) {
    System.out.println(s);
  }
}
