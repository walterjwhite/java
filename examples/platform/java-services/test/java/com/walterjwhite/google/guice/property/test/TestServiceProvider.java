package com.walterjwhite.google.guice.property.test;

import com.google.inject.Module;
import java.util.Iterator;
import java.util.ServiceLoader;

public class TestServiceProvider {

  /*
  @

  public void testServiceProvider(){

  }
  */

  public static void main(final String[] arguments) {
    final ServiceLoader<Module> moduleServiceLoader = ServiceLoader.load(Module.class);

    final Iterator<Module> moduleIterator = moduleServiceLoader.iterator();
    while (moduleIterator.hasNext()) {
      handleModule(moduleIterator.next());
    }
  }

  private static void handleModule(Module module) {}
}
