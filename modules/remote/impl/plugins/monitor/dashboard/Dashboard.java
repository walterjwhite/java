package com.walterjwhite.remote.impl.service.dashboard;

import com.walterjwhite.remote.impl.service.configuration.DashboardMonitorConfiguration;
import com.walterjwhite.remote.impl.service.configuration.MonitorConfiguration;
import com.walterjwhite.remote.impl.service.configuration.MonitorGroupConfiguration;
import com.walterjwhite.remote.impl.service.monitor.AbstractMonitor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.yaml.snakeyaml.Yaml;

public class Dashboard {
  protected final JobExecutorService jobExecutorService;
  private final Properties configuration = new Properties();

  public Dashboard(final String configurationFile)
      throws IOException,
          ClassNotFoundException,
          NoSuchMethodException,
          InstantiationException,
          IllegalAccessException,
          IllegalArgumentException,
          InvocationTargetException,
          NoSuchFieldException {

    Yaml yaml = new Yaml();
    int monitorCount = 0;
    try (InputStream in = Files.newInputStream(Paths.get(configurationFile))) {
      DashboardMonitorConfiguration config = yaml.loadAs(in, DashboardMonitorConfiguration.class);
      for (MonitorGroupConfiguration monitorGroupConfiguration : config.getMonitors()) {
        for (MonitorConfiguration monitorConfiguration :
            monitorGroupConfiguration.getConfigurations()) {
          final AbstractMonitor monitorInstance = get(monitorConfiguration);
          monitorGroupConfiguration.getMonitors().add(monitorInstance);
          monitorCount++;
        }
      }

      executorService = new ScheduledThreadPoolExecutor(monitorCount + 1);

      for (MonitorGroupConfiguration monitorGroupConfiguration : config.getMonitors()) {
        for (AbstractMonitor monitor : monitorGroupConfiguration.getMonitors()) {
          if (monitor.getRefreshInterval() > 0) {
            executorService.schedule(monitor, monitor.getRefreshInterval(), TimeUnit.MILLISECONDS);
          } else {
            executorService.submit(monitor);
          }
        }
      }

      final DashboardFrame dashboardFrame = new DashboardFrame(config.getMonitors());
      dashboardFrame.pack();
      dashboardFrame.setVisible(true);

      executorService.scheduleWithFixedDelay(dashboardFrame, 0, 1, TimeUnit.SECONDS);
    }
  }

  protected AbstractMonitor get(final MonitorConfiguration monitorConfiguration)
      throws ClassNotFoundException,
          NoSuchMethodException,
          InstantiationException,
          IllegalAccessException,
          IllegalArgumentException,
          InvocationTargetException,
          NoSuchFieldException {
    final Class monitorClass = Class.forName(monitorConfiguration.getClassName());
    final AbstractMonitor monitorInstance =
        (AbstractMonitor) monitorClass.getConstructor().getDeclaredConstructor().newInstance();

    for (final String fieldName : monitorConfiguration.getProperties().keySet()) {
      set(
          monitorClass,
          monitorInstance,
          fieldName,
          monitorConfiguration.getProperties().get(fieldName));
    }

    return (monitorInstance);
  }

  protected Field getField(final Class monitorClass, final String fieldName)
      throws NoSuchFieldException {
    try {
      return (monitorClass.getDeclaredField(fieldName));
    } catch (NoSuchFieldException e) {
      if (!monitorClass.equals(AbstractMonitor.class)) {
        return (getField(monitorClass.getSuperclass(), fieldName));
      }

      throw e;
    }
  }

  protected void set(
      final Class<AbstractMonitor> monitorClass,
      final AbstractMonitor monitorInstance,
      final String fieldName,
      final String fieldValue)
      throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    final Field field = getField(monitorClass, fieldName);

    final boolean wasAccessible = field.isAccessible();
    if (!wasAccessible) {
      field.setAccessible(true);
    }

    if (field.getType().equals(Integer.class)) {
      field.set(monitorInstance, Integer.valueOf(fieldValue));
    } else if (field.getType().equals(Double.class)) {
      field.set(monitorInstance, Double.valueOf(fieldValue));
    } else if (field.getType().equals(String.class)) {
      field.set(monitorInstance, fieldValue);
    }

    if (!wasAccessible) {
      field.setAccessible(wasAccessible);
    }
  }

  public static final void main(final String[] arguments) throws Exception {
    String configurationFile;
    if (arguments != null && arguments.length == 1) {
      configurationFile = arguments[0];
    } else {
      configurationFile = "dashboard.yaml";
    }

    Dashboard dashboard = new Dashboard(configurationFile);
  }
}
