package com.walterjwhite.examples.identity;

import com.google.inject.AbstractModule;
import com.walterjwhite.encryption.modules.guice.EncryptionModule;
import com.walterjwhite.infrastructure.inject.providers.guice.GuiceApplicationModule;

public class IdentityExampleGuiceModule extends AbstractModule implements GuiceApplicationModule {

  @Override
  protected void configure() {
    //    install(new CriteriaBuilderModule());
    //    install(new GoogleGuicePersistModule(/*propertyManager, reflections*/ ));
    install(new EncryptionModule());

    // this should be in the module, not here ...
    //    bind(Repository.class).to(JdoRepository.class);
    //    bind(QueryBuilderResolver.class).to(DefaultQueryBuilderResolver.class);

    bind(EntityCreator.class);
  }
}
