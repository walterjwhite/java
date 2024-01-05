package com.walterjwhite.examples.practice;

import com.walterjwhite.examples.practice.problems.organize.fibonacci.Fibonacci;
import com.walterjwhite.examples.practice.problems.organize.trees.Trees;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import jakarta.inject.Inject;
import lombok.NoArgsConstructor;

@NoArgsConstructor(onConstructor_ = @Inject)
public class PracticeCommandLineHandler implements CommandLineHandler {

  //  @Inject
  //  public PracticeCommandLineHandler(
  //      @Property(CommandLineHandlerShutdownTimeout.class) int shutdownTimeoutInSeconds) {
  //    super(shutdownTimeoutInSeconds);
  //  }

  @Override
  public void run(String... arguments) {

    Fibonacci fibonacci = new Fibonacci();
    fibonacci.print();

    Trees trees = new Trees();
    trees.print();
    // PathRecursion pathRecursion = new PathRecursion();
    // Assert.assertTrue
    // final PathRecursion2 pathRecursion2 = new PathRecursion2();
    // Node(1,0)));
  }
}
