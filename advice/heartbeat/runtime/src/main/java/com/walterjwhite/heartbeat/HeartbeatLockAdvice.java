package com.walterjwhite.heartbeat;

import com.walterjwhite.heartbeat.runtime.HeartbeatInstance;
import net.bytebuddy.asm.Advice;

public class HeartbeatLockAdvice {
  @Advice.OnMethodEnter
  public static void onEnter(@Advice.This Object intercepted) {
  }

  @Advice.OnMethodExit
  public static void onExit(@Advice.Enter final HeartbeatInstance heartbeatInstance) {
  }
}
