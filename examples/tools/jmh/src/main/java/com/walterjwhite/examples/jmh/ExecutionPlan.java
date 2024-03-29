package com.walterjwhite.examples.jmh;

import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class ExecutionPlan {
  @Param({"100", "200", "300", "500", "1000"})
  public int iterations;

  public Hasher murmur3;

  public String randomString = "4v3rys3kur3p455w0rd";

  @Setup(Level.Invocation)
  public void setUp() {
    murmur3 = Hashing.murmur3_128().newHasher();
  }
}
