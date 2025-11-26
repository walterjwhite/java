package com.walterjwhite.examples.cli;

import javax.jdo.PersistenceManager;
import javax.jdo.listener.CreateLifecycleListener;
import javax.jdo.listener.DirtyLifecycleListener;
import javax.jdo.listener.InstanceLifecycleEvent;
import javax.jdo.listener.StoreLifecycleListener;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SampleLifecycleListener
    implements CreateLifecycleListener, StoreLifecycleListener, DirtyLifecycleListener {
  protected final PersistenceManager persistenceManager;

  @Override
  public void postCreate(InstanceLifecycleEvent event) {}

  @Override
  public void preDirty(InstanceLifecycleEvent event) {}

  @Override
  public void postDirty(InstanceLifecycleEvent event) {}

  @Override
  public void preStore(InstanceLifecycleEvent event) {}

  @Override
  public void postStore(InstanceLifecycleEvent event) {}
}
