package com.walterjwhite.vaadin.data.pipe;

import com.bsb.common.vaadin.embed.support.EmbedVaadin;
import com.google.common.collect.EvictingQueue;
import com.vaadin.ui.*;
import com.walterjwhite.data.pipe.impl.AbstractSink;
import java.util.Queue;

/** Read-only scrolling view of data */
public class VaadinDataSink extends AbstractSink<Object[], VaadinDataSinkConfiguration> {
  protected Class dataClass;
  protected Grid grid;

  /**
   * Simply limits the # of elements stored, the oldest item gets dropped when the list is full and
   * a new item is added.
   */
  protected Queue dataFifo;

  //  protected VaadinDataSinkWidget widget;

  @Override
  protected void doConfigure() {
    //    try {
    //      this.dataClass = Class.forName(sinkConfiguration.getDataClassName());
    //    } catch (ClassNotFoundException e) {
    //      throw new RuntimeException("Error configuring", e));
    //    }

    this.grid = new Grid();
    this.dataFifo = EvictingQueue.create(sinkConfiguration.getMaxRows());

    grid.setItems(dataFifo);

    //    grid.setColumns(sinkConfiguration.getColumnNames());
    grid.setSizeFull();

    // EmbedVaadin.forComponent(grid).withHttpPort(8100).start();
    EmbedVaadin.forComponent(grid).start();
    //
    //    widget = new VaadinDataSinkWidget(this);
    //    try {
    //      widget.init();
    //    } catch (ServiceException e) {
    //      throw(new RuntimeException("Error configuring", e));
    //    }
  }

  @Override
  public void close() {}

  @Override
  public void accept(Object[] o) {
    dataFifo.add(o);
  }

  //  public void updateGrid() {
  //    final List<? extends AbstractEntity> entities =
  // repositoryProvider.get().findAll(entityClass);
  //    grid.setItems((List) entities);
  //  }

  //  /**
  //   * TODO: enable filtering by each field
  //   *

  //   */
  //  protected Layout setFilter() {
  //    filterText.setPlaceholder("filter by name...");
  //    filterText.addValueChangeListener(e -> updateGrid());
  //    filterText.setValueChangeMode(ValueChangeMode.LAZY);
  //
  //    Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
  //    clearFilterTextBtn.setDescription("Clear the current filter");
  //    clearFilterTextBtn.addClickListener(e -> filterText.clear());
  //
  //    CssLayout filtering = new CssLayout();
  //    filtering.addComponents(filterText, clearFilterTextBtn);
  //    filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
  //
  //    HorizontalLayout toolbar = new HorizontalLayout(filtering, addEntity());
  //    return (toolbar);
  //  }
  //
  //  protected void configureEntitySelection() {
  //    grid.asSingleSelect()
  //            .addValueChangeListener(
  //                    event -> {
  //                      if (event.getValue() == null) {
  //                        form.setVisible(false);
  //                      } else {
  //                        form.setInstance(event.getValue());
  //                      }
  //                    });
  //  }

  public Grid getGrid() {
    return grid;
  }
}
