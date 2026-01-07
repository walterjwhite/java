package com.walterjwhite.examples.identity;


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
    GuestAccount guestAccount = new GuestAccount();
    guestAccount.getAttributes().put("remoteIP", "8.8.8.8");

    repository.create(guestAccount);

  }

  @Transactional
  public void createClientAccount() {
    ClientAccount clientAccount = new ClientAccount();
    clientAccount.setClientId(new Encrypted("clientIdSecret", EncryptionType.Encrypt));

    repository.create(clientAccount);

  }
}
