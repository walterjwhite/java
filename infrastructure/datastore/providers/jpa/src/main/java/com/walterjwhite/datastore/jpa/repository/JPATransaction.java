package com.walterjwhite.datastore.jpa.repository;

import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Synchronization;
import jakarta.transaction.Transaction;
import javax.transaction.xa.XAResource;
import lombok.Getter;

@Getter
public class JPATransaction implements Transaction {
  protected final EntityTransaction delegate;

  public JPATransaction(final EntityTransaction delegate) {
    this.delegate = delegate;
    delegate.begin();
  }

  @Override
  public void commit() throws SecurityException, IllegalStateException {
    delegate.commit();
  }

  @Override
  public boolean delistResource(XAResource xaResource, int i) throws IllegalStateException {
    return false;
  }

  @Override
  public boolean enlistResource(XAResource xaResource) throws IllegalStateException {
    return false;
  }

  @Override
  public int getStatus() {
    return 0;
  }

  @Override
  public void registerSynchronization(Synchronization synchronization)
      throws IllegalStateException {}

  @Override
  public void rollback() throws IllegalStateException {
    delegate.rollback();
  }

  @Override
  public void setRollbackOnly() throws IllegalStateException {
    delegate.setRollbackOnly();
  }
}
