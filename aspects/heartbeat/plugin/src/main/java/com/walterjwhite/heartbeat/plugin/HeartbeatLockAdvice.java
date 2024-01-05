package com.walterjwhite.heartbeat.plugin;

import com.walterjwhite.heartbeat.impl.HeartbeatInstance;
import net.bytebuddy.asm.Advice;

public class HeartbeatLockAdvice {
  @Advice.OnMethodEnter
  public static void onEnter(@Advice.This Object intercepted) {
    // lock first argument
  }

  @Advice.OnMethodExit
  public static void onExit(@Advice.Enter final HeartbeatInstance heartbeatInstance) {
    // unlock first argument
  }
}
