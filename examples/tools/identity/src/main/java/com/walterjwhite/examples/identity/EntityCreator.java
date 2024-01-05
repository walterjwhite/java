package com.walterjwhite.examples.identity;

// import com.google.inject.create.Transactional;

import com.walterjwhite.datastore.api.repository.Repository;
import com.walterjwhite.encryption.enumeration.EncryptionType;
import com.walterjwhite.encryption.model.Encrypted;
import com.walterjwhite.identity.api.model.account.ClientAccount;
import com.walterjwhite.identity.api.model.account.GuestAccount;
import jakarta.inject.Inject;
import javax.transaction.Transactional;

public class EntityCreator {
  protected final Repository repository;

  @Inject
  public EntityCreator(Repository repository) {
    this.repository = repository;
  }

  @Transactional
  public void createGuestAccount() {
    // EntityTransaction entityTransaction = repository.getEntityTransaction();
    // entityTransaction.begin();
    GuestAccount guestAccount = new GuestAccount();
    guestAccount.getAttributes().put("remoteIP", "8.8.8.8");

    repository.create(guestAccount);
    // entityTransaction.commit();

    // repository.getEntityManager().flush();
  }

  @Transactional
  public void createClientAccount() {
    // EntityTransaction entityTransaction = repository.getEntityTransaction();
    // entityTransaction.begin();
    ClientAccount clientAccount = new ClientAccount();
    clientAccount.setClientId(new Encrypted("clientIdSecret", EncryptionType.Encrypt));

    repository.create(clientAccount);
    // entityTransaction.commit();

    // repository.getEntityManager().flush();
  }
}
