package com.walterjwhite.delay.plugin;

import com.walterjwhite.delay.Delayable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DelayAdvice {

  @Advice.OnMethodEnter
  public static void onEnter(@Advice.AllArguments Object[] allArguments)
      throws InterruptedException {
    if (allArguments != null && allArguments.length > 0) {
      for (Object argument : allArguments) {
        if (argument instanceof Delayable) {
          final Delayable delayable = (Delayable) argument;
          delayable.delay();
        }
      }
    }
  }
}
