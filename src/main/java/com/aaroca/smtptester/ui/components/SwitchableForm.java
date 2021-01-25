package com.aaroca.smtptester.ui.components;

import java.awt.Component;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.apache.commons.lang3.ArrayUtils;

public class SwitchableForm extends JComponent {

  private JCheckBox checkBox;
  private JPanel panel;
  private List<JComponent> fields;

  public SwitchableForm(String label, String title, JComponent... fields) {
    if (ArrayUtils.isEmpty(fields)) {
      throw new IllegalArgumentException("Provide at least one field.");
    }

    this.checkBox = new JCheckBox(label);
    this.panel = new JPanel();
    this.panel.setBorder(BorderFactory.createTitledBorder(title));
    this.fields = Arrays.stream(fields).collect(Collectors.toList());

    init();
  }

  private void init() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setAlignmentX(Component.LEFT_ALIGNMENT);

    addSwitch();
    addContentPanel();
    addFields();
  }

  private void addSwitch() {
    checkBox.addChangeListener(event -> {
      panel.setEnabled(checkBox.isSelected());
      fields.forEach(field -> field.setEnabled(checkBox.isSelected()));
    });

    add(checkBox);
  }

  private void addContentPanel() {
    panel.setLayout(new BoxLayout(this.panel, BoxLayout.Y_AXIS));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    panel.setEnabled(false);

    add(panel);
  }

  private void addFields() {
    fields.forEach(field -> {
      field.setEnabled(false);
      panel.add(field);
    });
  }

  public boolean isSelected() {
    return checkBox.isSelected();
  }

  public void clear() {
    checkBox.setSelected(false);
  }
}
