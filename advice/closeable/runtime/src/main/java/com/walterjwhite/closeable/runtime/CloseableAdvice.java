package com.walterjwhite.closeable.runtime;

import com.walterjwhite.closeable.impl.CloseableUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CloseableAdvice {
  @Advice.OnMethodExit
  public static void onExit(@Advice.This Object intercepted) {
    CloseableUtil.addAutoCloseable((AutoCloseable) intercepted);
  }
}
