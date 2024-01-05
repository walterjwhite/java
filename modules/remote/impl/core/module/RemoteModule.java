package com.walterjwhite.remote.impl.module;

import com.walterjwhite.compression.modules.CompressionModule;
import com.walterjwhite.datastore.GoogleGuicePersistModule;
import com.walterjwhite.datastore.api.repository.CriteriaBuilderModule;
import com.walterjwhite.encryption.impl.EncryptionModule;
import com.walterjwhite.google.guice.GuavaEventBusModule;
import com.walterjwhite.google.guice.property.AbstractPropertyModule;
import com.walterjwhite.job.api.model.QueueMonitor;
import com.walterjwhite.job.impl.QueueModule;
import com.walterjwhite.property.api.PropertyManager;
import com.walterjwhite.remote.api.model.Client;
import com.walterjwhite.remote.api.service.ClientIdentifierService;
import com.walterjwhite.remote.api.service.MessageWriterService;
import com.walterjwhite.remote.impl.DefaultMessageWriterService;
import com.walterjwhite.remote.impl.provider.ClientProvider;
import com.walterjwhite.remote.impl.provider.QueueMonitorProvider;
import com.walterjwhite.remote.impl.service.DefaultClientIdentifierService;
import com.walterjwhite.serialization.modules.java.KryoSerializationServiceModule;
import org.reflections.Reflections;

public class RemoteModule extends AbstractPropertyModule {
  public RemoteModule(PropertyManager propertyManager, Reflections reflections) {
    super(propertyManager, reflections);
  }

  @Override
  protected void doConfigure() {
    bind(Client.class).toProvider(ClientProvider.class); // .in(Singleton.class);
    bind(ClientIdentifierService.class).to(DefaultClientIdentifierService.class);
    bind(MessageWriterService.class).to(DefaultMessageWriterService.class);
    bind(QueueMonitor.class).toProvider(QueueMonitorProvider.class);

    install(new CriteriaBuilderModule());
    install(new GuavaEventBusModule());
    install(new QueueModule());

    install(new GoogleGuicePersistModule(/*propertyManager, reflections*/ ));

    // install(new JacksonSerializationServiceModule());
    //    install(new NativeSerializationServiceModule());
    install(new KryoSerializationServiceModule());
    install(new CompressionModule());
    install(new EncryptionModule());
  }
}
