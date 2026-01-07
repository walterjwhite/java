package com.walterjwhite.vaadin.data.pipe;

import com.bsb.common.vaadin.embed.support.EmbedVaadin;
import com.google.common.collect.EvictingQueue;
import com.vaadin.ui.*;
import com.walterjwhite.data.pipe.impl.AbstractSink;
import java.util.Queue;

public class VaadinDataSink extends AbstractSink<Object[], VaadinDataSinkConfiguration> {
  protected Class dataClass;
  protected Grid grid;

  protected Queue dataFifo;


  @Override
  protected void doConfigure() {

    this.grid = new Grid();
    this.dataFifo = EvictingQueue.create(sinkConfiguration.getMaxRows());

    grid.setItems(dataFifo);

    grid.setSizeFull();

    EmbedVaadin.forComponent(grid).start();
  }

  @Override
  public void close() {}

  @Override
  public void accept(Object[] o) {
    dataFifo.add(o);
  }




  public Grid getGrid() {
    return grid;
  }
}
