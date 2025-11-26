package com.walterjwhite.examples.cli;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.inject.cli.service.CommandLineHandler;
import com.walterjwhite.logging.annotation.Sensitive;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.SystemException;
import jakarta.transaction.Transaction;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CLIExampleCommandLineHandler implements CommandLineHandler {
  protected final Repository<EntityManager> repository;
  protected final QueryByNameEqualsJPAEntity queryByNameEqualsJPAEntity;

  @Override
  public void run(final String... arguments) throws SystemException {
    final JPANamedEntity namedEntity = new JPANamedEntity();
    namedEntity.setName("name");
    namedEntity.setDescription("description");

    LOGGER.info("created {}", repository.create(namedEntity));

    final Set<Integer> ids = save(arguments);

    find(ids);

    findByName(arguments);
  }

  protected Set<Integer> save(@Sensitive(1) final String... arguments) throws SystemException {
    final Transaction transaction = repository.getTransaction();

    final Set<Integer> recordsInserted = new HashSet<>();
    try {
      for (final String arg : arguments) {
        final JPAEntity e = new JPAEntity(arg);
        final List<JPAReferenceEntity> references = new ArrayList<>();
        references.add(new JPAReferenceEntity("green", e));
        references.add(new JPAReferenceEntity("blue", e));
        references.add(new JPAReferenceEntity("red", e));

        e.setReferences(references);
        final JPAEntity persisted = repository.create(e);
        LOGGER.info("persisted: {}", persisted.getId());

        recordsInserted.add(persisted.getId());
      }

      transaction.commit();
    } catch (Exception e) {
      LOGGER.error("Error committing tx", e);
      transaction.rollback();
    }

    repository.getDelegate().clear();

    return recordsInserted;
  }

  protected void find(final Set<Integer> ids) {
    for (final Integer id : ids) {
      final JPAEntity e = repository.findById(JPAEntity.class, id);
      LOGGER.info("found: {}", e.getId());
      LOGGER.info("references: {}, size: {}", e.getReferences(), e.getReferences().size());
    }
  }

  protected void findByName(final String... arguments) {
    for (final String name : arguments) {
      getByName(name);
    }
  }

  @Sensitive
  protected String getByName(final String name) {
    final JPAEntity e = queryByNameEqualsJPAEntity.get(name);
    LOGGER.info("find by name: {}", e);

    return e.getName();
  }
}
