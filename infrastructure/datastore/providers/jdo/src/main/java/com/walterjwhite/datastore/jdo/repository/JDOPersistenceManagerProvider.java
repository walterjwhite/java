package com.walterjwhite.datastore.jdo.repository;

import com.walterjwhite.datastore.jdo.*;
import com.walterjwhite.property.api.annotation.Property;
import jakarta.inject.Inject;
import jakarta.inject.Provider;
import java.util.Properties;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import org.datanucleus.api.jdo.JDOPersistenceManagerFactory;

public class JDOPersistenceManagerProvider implements Provider<PersistenceManager> {
  protected final String persistenceUnitName;
  protected final String connectionDriverName;
  protected final boolean datanucleusAutoCreateAll;
  protected final String mapping;
  protected final String connectionUrl;
  protected final String connectionUsername;
  protected final String connectionPassword;
  protected final PersistenceManagerFactory persistenceManagerFactory;

  @SuppressWarnings({"JdkObsolete", "BanJNDI"})
  @Inject
  public JDOPersistenceManagerProvider(
      @Property(PersistenceUnitName.class) final String persistenceUnitName,
      @Property(JDOConnectionUrl.class) final String connectionUrl,
      @Property(JDOConnectionUsername.class) final String connectionUsername,
      @Property(JDOConnectionPassword.class) final String connectionPassword,
      @Property(Mapping.class) final String mapping,
      @Property(JDOConnectionDriverName.class) final String connectionDriverName,
      @Property(JDODatanucleusAutoCreateAll.class) final boolean datanucleusAutoCreateAll) {
    this.persistenceUnitName = persistenceUnitName;
    this.connectionUrl = connectionUrl;
    this.connectionUsername = connectionUsername;
    this.connectionPassword = connectionPassword;
    this.mapping = mapping;
    this.connectionDriverName = connectionDriverName;
    this.datanucleusAutoCreateAll = datanucleusAutoCreateAll;

    persistenceManagerFactory = JDOHelper.getPersistenceManagerFactory(build());
  }

  protected Properties build() {
    final Properties properties = new Properties();

    properties.setProperty(
        "javax.jdo.PersistenceManagerFactoryClass", JDOPersistenceManagerFactory.class.getName());
    properties.setProperty("javax.jdo.option.PersistenceUnitName", persistenceUnitName);
    properties.setProperty("javax.jdo.option.ConnectionDriverName", connectionDriverName);
    properties.setProperty("javax.jdo.option.ConnectionURL", connectionUrl);
    properties.setProperty("javax.jdo.option.ConnectionUserName", connectionUsername);
    properties.setProperty("javax.jdo.option.ConnectionPassword", connectionPassword);
    properties.setProperty("javax.jdo.option.Mapping", mapping);

    properties.setProperty(
        "datanucleus.schema.autoCreateAll", Boolean.toString(datanucleusAutoCreateAll));

    return properties;
  }

  @Override
  public PersistenceManager get() {
    return persistenceManagerFactory.getPersistenceManager();

  }
}
