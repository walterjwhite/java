package com.walterjwhite.examples.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

public class TestBenchmark {
  private static final int ITERATIONS = 10000;

  @BenchmarkMode(Mode.Throughput)
  @Benchmark
  public void testConcatenation() {
    String input = "";

    for (int i = 0; i < ITERATIONS; i++) {
      input = input + i + " ";
    }
  }

  @BenchmarkMode(Mode.Throughput)
  @Benchmark
  public void testStringBuilder() {
    final StringBuilder buffer = new StringBuilder();

    for (int i = 0; i < ITERATIONS; i++) {
      buffer.append(i).append(" ");
    }
  }

  @BenchmarkMode(Mode.Throughput)
  @Benchmark
  public void testStringBuffer() {
    final StringBuffer buffer = new StringBuffer();

    for (int i = 0; i < ITERATIONS; i++) {
      buffer.append(i).append(" ");
    }
  }
}
