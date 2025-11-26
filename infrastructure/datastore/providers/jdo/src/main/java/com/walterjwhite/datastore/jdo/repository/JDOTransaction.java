package com.walterjwhite.datastore.jdo.repository;

import jakarta.transaction.*;
import javax.transaction.xa.XAResource;
import lombok.Getter;

@Getter
public class JDOTransaction implements Transaction {
  protected final javax.jdo.Transaction delegate;

  public JDOTransaction(javax.jdo.Transaction delegate) {
    this.delegate = delegate;
    delegate.begin();
  }

  @Override
  public void commit()
      throws RollbackException,
          HeuristicMixedException,
          HeuristicRollbackException,
          SecurityException,
          IllegalStateException,
          SystemException {
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
