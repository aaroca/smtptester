package com.aaroca.smtptester.ui.components;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.apache.commons.lang3.ArrayUtils;

public class SwitchableForm extends JComponent implements ChangeListener {

  private JCheckBox checkBox;
  private JPanel contentPanel;
  private List<JComponent> fields;

  public SwitchableForm(String label, String title, JComponent... fields) {
    init();
    buildComponents(label, title, fields);
    addComponents();
  }

  @Override
  public void stateChanged(ChangeEvent event) {
    if (event.getSource() == checkBox) {
      toggle(checkBox.isSelected());
    }
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    checkBox.setEnabled(enabled);
    toggle(enabled && checkBox.isSelected());
  }

  public void clear() {
    checkBox.setSelected(false);
  }

  public void addActionListener(ActionListener listener) {
    checkBox.addActionListener(listener);
  }

  private void init() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAlignmentX(Component.LEFT_ALIGNMENT);
  }

  private void buildComponents(String label, String title, JComponent... fields) {
    if (ArrayUtils.isEmpty(fields)) {
      throw new IllegalArgumentException("Provide at least one field.");
    }

    this.fields = Arrays.stream(fields).collect(Collectors.toList());
    checkBox = new JCheckBox(label);
    checkBox.addChangeListener(this);
    contentPanel = new JPanel();
    contentPanel.setBorder(BorderFactory.createTitledBorder(title));
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    contentPanel.setEnabled(false);
  }

  private void addComponents() {
    add(checkBox);
    add(contentPanel);
    fields.forEach(field -> {
      field.setEnabled(false);
      contentPanel.add(field);
    });
  }

  private void toggle(Boolean enabled) {
    contentPanel.setEnabled(enabled);
    fields.forEach(field -> field.setEnabled(enabled));
  }

  public boolean isSelected() {
    return this.checkBox.isSelected();
  }

  public void setSelected(boolean selected) {
    this.checkBox.setSelected(selected);
  }

  public JCheckBox getEventSource() {
    return checkBox;
  }
}
