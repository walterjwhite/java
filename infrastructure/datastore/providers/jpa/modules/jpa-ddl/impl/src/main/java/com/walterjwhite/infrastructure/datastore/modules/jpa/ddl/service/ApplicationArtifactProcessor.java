package com.walterjwhite.infrastructure.datastore.modules.jpa.ddl.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class ApplicationArtifactProcessor {
  public static final String IMPLEMENTATION_BUILD_KEY = "Implementation-Build";

  protected final String applicationArtifactPath;

  public ApplicationArtifactProcessor(String applicationArtifactPath)
      throws InvocationTargetException, MalformedURLException, IllegalAccessException,
          NoSuchMethodException {
    this.applicationArtifactPath = applicationArtifactPath;

    addJarToClassPath();
  }

  protected void addJarToClassPath()
      throws MalformedURLException, NoSuchMethodException, InvocationTargetException,
          IllegalAccessException {
    final URL url = new File(applicationArtifactPath).toURI().toURL();

    final URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
    final Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
    method.setAccessible(true);
    method.invoke(classLoader, url);
  }

  public Set<Class> getEntityClasses() throws IOException {
    try (JarInputStream jarInputStream =
        new JarInputStream(new BufferedInputStream(new FileInputStream(applicationArtifactPath)))) {
      JarEntry jarEntry;

      final Set<Class> classesInJar = new HashSet<>();
      while (true) {
        jarEntry = jarInputStream.getNextJarEntry();
        if (jarEntry == null) {
          break;
        }
        if ((jarEntry.getName().endsWith(".class"))) {
          String className = jarEntry.getName().replaceAll("/", "\\.");

          try {
            final Class classEntry =
                Class.forName(className.substring(0, className.lastIndexOf('.')));
            if (isEntity(classEntry)) classesInJar.add(classEntry);
          } catch (Exception | Error e) {
            handleClassLoadError(e);
          }
        }
      }

      return classesInJar;
    }
  }

  protected void handleClassLoadError(Throwable e) {}

  protected boolean isEntity(Class candidateEntityClass) {
    if (candidateEntityClass.isAnnotationPresent(Entity.class)) return true;
    if (candidateEntityClass.isAnnotationPresent(MappedSuperclass.class)) return true;

    return false;
  }

  public String getImplementationVersion() throws IOException {
    try (JarInputStream jarInputStream =
        new JarInputStream(new BufferedInputStream(new FileInputStream(applicationArtifactPath)))) {

      final String implementationBuild =
          jarInputStream.getManifest().getMainAttributes().getValue(IMPLEMENTATION_BUILD_KEY);

      if (implementationBuild != null) {
        if (!implementationBuild.contains("${buildNumber}")) return implementationBuild;
      }

      return jarInputStream
          .getManifest()
          .getMainAttributes()
          .getValue(Attributes.Name.IMPLEMENTATION_VERSION);
    }
  }
}
