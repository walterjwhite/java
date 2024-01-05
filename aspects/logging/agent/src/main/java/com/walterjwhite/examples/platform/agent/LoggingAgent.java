package com.walterjwhite.examples.platform.agent;

import com.walterjwhite.logging.plugin.LoggingPlugin;
import java.lang.instrument.Instrumentation;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

// see: https://sergiomartinrubio.com/articles/java-code-manipulation-with-byte-buddy/
public class LoggingAgent {
  public static void premain(final String premainArgs, final Instrumentation instrumentation) {
    new AgentBuilder.Default()
        .type(ElementMatchers.any())
        .transform(
            (builder, typeDescription, classLoader, module, protectionDomain) ->
                transform(premainArgs, builder, typeDescription, classLoader, module))
        .installOn(instrumentation);
  }

  private static DynamicType.Builder<?> transform(
      final String premainArgs,
      DynamicType.Builder<?> builder,
      TypeDescription typeDescription,
      ClassLoader classLoader,
      JavaModule module) {
    if (typeDescription.getPackage().getName().startsWith(premainArgs)) {
      return LoggingPlugin.apply(builder, typeDescription);
    }

    return null;
  }
}
