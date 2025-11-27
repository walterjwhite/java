package com.walterjwhite.infrastructure.inject.web.guice;

import com.google.inject.servlet.ServletModule;
import com.walterjwhite.file.modules.resources.JarReadUtils;
import com.walterjwhite.infrastructure.inject.core.helper.ApplicationHelper;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;
import java.util.regex.Pattern;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuiceServletApplicationModule extends ServletModule implements GuiceApplicationModule {
  @Override
  protected void configureServlets() {
    registerFilters();
    registerServlets();
  }

  protected void registerServlets() {
    configure("servlets.properties", this::registerServlet);
  }

  @SneakyThrows
  protected Void registerServlet(final String argument) {
    final String[] arguments = split(argument);
    final Class<? extends HttpServlet> httpServletClass =
        (Class<? extends HttpServlet>) Class.forName(arguments[1]);
    serve(arguments[0]).with(httpServletClass);

    return null;
  }

  private static String[] split(final String input) {
    return input.split("=>");
  }

  protected void registerFilters() {
    configure("filters.properties", this::registerFilter);
  }

  @SneakyThrows
  protected Void registerFilter(final String argument) {
    final String[] arguments = split(argument);
    final Class<? extends Filter> filterClass =
        (Class<? extends Filter>) Class.forName(arguments[1]);
    filter(arguments[0]).through(filterClass);
    return null;
  }

  private static void configure(final String pattern, Function<String, Void> f) {
    for (final String resource :
        ApplicationHelper.getApplicationInstance()
            .getReflections()
            .getResources(Pattern.compile(pattern))) {
      try (final BufferedReader bufferedReader =
          new BufferedReader(
              new InputStreamReader(JarReadUtils.getFileFromResourceAsStream(resource)))) {
        while (bufferedReader.ready()) {
          f.apply(bufferedReader.readLine());
        }
      } catch (IOException e) {
        throw new Error("Unable to configure", e);
      }
    }
  }
}
