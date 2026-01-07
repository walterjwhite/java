package com.walterjwhite.heartbeat.runtime;

import net.bytebuddy.asm.Advice;

public class HeartbeatAdvice {

  @Advice.OnMethodEnter
  public static HeartbeatInstance onEnter(@Advice.This Object intercepted) {
    return new HeartbeatInstance(intercepted);
  }

  @Advice.OnMethodExit
  public static void onExit(@Advice.Enter final HeartbeatInstance heartbeatInstance) {
    heartbeatInstance.cancel();
  }
}
