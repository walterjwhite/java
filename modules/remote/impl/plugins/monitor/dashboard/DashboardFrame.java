package com.walterjwhite.remote.impl.service.dashboard;

import com.walterjwhite.remote.impl.service.configuration.MonitorGroupConfiguration;
import com.walterjwhite.remote.impl.service.database.DataTable;
import com.walterjwhite.remote.impl.service.monitor.AbstractMonitor;
import com.walterjwhite.remote.impl.service.monitor.Log;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class DashboardFrame extends JFrame implements Runnable {
  protected final List<AbstractMonitor> monitors = new ArrayList<>();
  protected final List<Component> monitorComponents = new ArrayList<Component>();

  public DashboardFrame(final List<MonitorGroupConfiguration> monitorGroupConfigurations) {
    setTitle("Dashboard");
    setSize(800, 800);
    setLocation(0, 0);
    // final int size = getRows(monitors.size());
    // setLayout(new GridLayout(size, monitors.size() / size + 1));

    final JPanel mainContainer = new JPanel();
    mainContainer.setLayout(new BoxLayout(mainContainer, BoxLayout.X_AXIS));
    // final JScrollPane mainContainer = new JScrollPane();

    add(mainContainer);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    for (final MonitorGroupConfiguration monitorGroupConfiguration : monitorGroupConfigurations) {
      final JPanel groupPanel = new JPanel();
      mainContainer.add(groupPanel);

      groupPanel.setLayout(new BoxLayout(groupPanel, BoxLayout.Y_AXIS));

      groupPanel.add(new JLabel("Group:" + monitorGroupConfiguration.getName()));

      for (final AbstractMonitor monitor : monitorGroupConfiguration.getMonitors()) {
        monitorComponents.add(addMonitor(groupPanel, monitor));
        monitors.add(monitor);
      }
    }
  }

  public Component addMonitor(final Container container, final AbstractMonitor monitor) {
    if (monitor.getResult() instanceof Log) {
      return (addScrollingLogWindow(container, monitor));
    }
    if (monitor.getResult() instanceof DataTable) {
      return (addTable(container, monitor));
    }

    throw new RuntimeException("Result is not supported:" + monitor.getResult());
  }

  /**
   * TODO: make table sortable / filterable
   *
   * @param monitor
   * @return
   */
  protected Component addTable(final Container container, final AbstractMonitor monitor) {
    final JTable jtable = new JTable();
    jtable.setAutoCreateRowSorter(true);

    final JScrollPane jScrollPane = new JScrollPane(jtable);
    final JPanel containerPanel = new JPanel();
    containerPanel.setPreferredSize(new Dimension(400, 400));
    final JLabel label = new JLabel(monitor.toString());

    label.addMouseListener(new MouseClickEventListener(jtable));

    containerPanel.add(label);
    containerPanel.add(jScrollPane);

    container.add(containerPanel);

    containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

    return (jtable);
  }

  protected class MouseClickEventListener implements MouseListener {

    protected final JTable table;

    public MouseClickEventListener(JTable table) {

      this.table = table;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      /*
              final TableRowSorter tableRowSorter = (TableRowSorter) table.getRowSorter();
              final RowFilter rowFilter = tableRowSorter.setRowFilter(filter);


      RowFilter<MyTableModel, Object> rf = null;
      //If current expression doesn't parse, don't update.
      try {
          rf = RowFilter.regexFilter(filterText.getText(), 0);
      } catch (java.util.regex.PatternSyntaxException e) {
          return;
      }
      sorter.setRowFilter(rf);
               */
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
  }

  /*
   protected class FilterFrame extends JFrame{
      setTitle("Enter Filter Criteria");
      setSize(800, 800);
      setLocation(0, 0);
      final int size = getRows(monitors.size());
      setLayout(new GridLayout(size, monitors.size() / size + 1));
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      for (final AbstractMonitor monitor : monitors) {
          monitorComponents.add(addMonitor(monitor));
      }

      this.monitors = monitors;
  }
   */
  protected Component addScrollingLogWindow(
      final Container container, final AbstractMonitor monitor) {
    final JTextArea jTextArea = new JTextArea(5, 30);
    final StringBuilder buffer = new StringBuilder();
    for (final String log : (Log<String>) monitor.getResult()) {
      buffer.append(log);
    }

    jTextArea.setText(buffer.toString());

    final JScrollPane jScrollPane = new JScrollPane(jTextArea);

    final JPanel containerPanel = new JPanel();
    containerPanel.setPreferredSize(new Dimension(400, 400));
    containerPanel.add(new JLabel(monitor.getClass().getSimpleName()));
    containerPanel.add(new JLabel(monitor.toString()));
    containerPanel.add(jScrollPane);

    container.add(containerPanel);

    containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.Y_AXIS));

    return (jTextArea);
  }

  /*
  public static void main(String[] args) {
      JFrame f = new DashboardFrame();
      f.pack();
      f.setVisible(true);
  }
   */
  @Override
  public void run() {
    int i = 0;
    for (final AbstractMonitor monitor : monitors) {
      // update the table / text area
      if (monitor.getResult() instanceof Log) {
        // return (addScrollingLogWindow(monitor));
        final JTextArea c = (JTextArea) monitorComponents.get(i);

        final StringBuilder buffer = new StringBuilder();
        for (final String log : (Log<String>) monitor.getResult()) {
          buffer.append(log + "\n");
        }

        c.setText(buffer.toString());
        c.repaint();
      }
      if (monitor.getResult() instanceof DataTable) {
        if (((DataTable) monitor.getResult()).getRows().size() > 0) {
          final DataTable result = (DataTable) monitor.getResult();
          final JTable c = (JTable) monitorComponents.get(i);
          if (c.getModel().getColumnCount() == 0) {
            for (final String columnName : result.getColumnNames()) {
              ((DefaultTableModel) c.getModel()).addColumn(columnName);
            }
          }

          for (int row = ((DefaultTableModel) c.getModel()).getRowCount() - 1; row >= 0; row--) {
            ((DefaultTableModel) c.getModel()).removeRow(row);
          }

          for (final List<String> row : result.getRows()) {
            ((DefaultTableModel) c.getModel()).addRow(row.toArray());
          }
        }
      }

      i++;
    }
  }
}
