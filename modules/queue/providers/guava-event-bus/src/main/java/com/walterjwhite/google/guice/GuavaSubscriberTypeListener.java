package com.walterjwhite.google.guice;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import com.walterjwhite.google.guice.annotation.EventListener;


public class GuavaSubscriberTypeListener implements TypeListener {
  protected final EventBusDelegate eventBusDelegate;

  public GuavaSubscriberTypeListener(EventBusDelegate eventBusDelegate) {

    this.eventBusDelegate = eventBusDelegate;
  }

  @Override
  public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
    if (isEventListener(typeLiteral)) {
      register(typeLiteral, typeEncounter);
    }
  }

  protected <I> boolean isEventListener(TypeLiteral<I> typeLiteral) {
    return typeLiteral.getRawType().isAnnotationPresent(EventListener.class);
  }

  protected <I> void register(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
    EventListener eventListener = typeLiteral.getRawType().getAnnotation(EventListener.class);
    typeEncounter.register(
        (InjectionListener<I>)
            i -> {
              eventBusDelegate.register(i, eventListener.value());
            });
  }
}
