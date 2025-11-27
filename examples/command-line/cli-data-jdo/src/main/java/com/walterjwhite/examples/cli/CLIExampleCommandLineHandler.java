package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.email.api.model.Email;
import com.walterjwhite.email.api.model.EmailAccount;
import com.walterjwhite.email.api.model.PrivateEmailAccount;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.logging.annotation.Sensitive;
import jakarta.inject.Inject;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.jdo.JDOQLTypedQuery;
import javax.jdo.PersistenceManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CLIExampleCommandLineHandler implements CommandLineHandler {
  protected final Repository<PersistenceManager> repository;

  @Override
  public void run(final String... arguments) throws SystemException {
    repository
        .getDelegate()
        .addInstanceLifecycleListener(new SampleLifecycleListener(repository.getDelegate()));

    LOGGER.info("created {}", repository.create(new UUIDEntity()));
    final NamedEntity namedEntity = new NamedEntity();
    namedEntity.setName("name");
    namedEntity.setDescription("description");

    LOGGER.info("created {}", repository.create(namedEntity));

    saveEmail();
    final Set<Integer> ids = save(arguments);

    find(ids);
  }

  protected Set<Integer> save(@Sensitive(1) final String... arguments) throws SystemException {
    final Transaction transaction = repository.getTransaction();

    final Set<Integer> recordsInserted = new HashSet<>();
    try {
      for (final String arg : arguments) {
        final Entity persisted = repository.create(new Entity(arg));
        LOGGER.info("persisted: {}", persisted.getId());

        recordsInserted.add(persisted.getId());
      }

      transaction.commit();
    } catch (Exception e) {
      transaction.rollback();
    }

    return recordsInserted;
  }

  protected void saveEmail() {
    final Email email =
        Email.builder()
            .subject("subject goes here")
            .body("body goes here")
            .from(
                new PrivateEmailAccount(
                    new EmailAccount().withName("Name").withEmailAddress("email@email.com")))
            .messageId("1234")
            .createdDate(LocalDateTime.now())
            .serverId("serverId")
            .build();
    final Email createdEmail = repository.create(email);
  }

  protected void find(final Set<Integer> ids) {
    JDOQLTypedQuery<Entity> greetingQuery =
        repository.getDelegate().newJDOQLTypedQuery(Entity.class);
    com.walterjwhite.examples.cli.QEntity qEntity =
        com.walterjwhite.examples.cli.QEntity.candidate();

    LOGGER.info("Expecting: {} records", ids.size());
    for (final Integer id : ids) {
      greetingQuery
          .filter(qEntity.id.eq(id))
          .executeList()
          .forEach(e -> LOGGER.info("found: {}", e.getId()));
    }
  }
}
