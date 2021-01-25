package com.aaroca.smtptester.ui.components;

import static com.aaroca.smtptester.utils.Constants.Ui.DEFAULT_SIZE;

import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import org.apache.commons.lang3.StringUtils;

public class FormField extends JComponent {

  private JLabel label;
  private JTextComponent field;

  public FormField(String label) {
    this(label, new JTextField(DEFAULT_SIZE));
  }

  public FormField(String label, JTextComponent field) {
    Objects.requireNonNull(field);

    this.label = new JLabel(label);
    this.field = field;

    init();
  }

  private void init() {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    setAlignmentX(Component.LEFT_ALIGNMENT);

    addFields();
  }

  private void addFields() {
    if (StringUtils.isNotEmpty(label.getText())) {
      add(label);
    }

    add(field);
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    label.setEnabled(enabled);
    field.setEnabled(enabled);
  }

  public void setLabel(String label) {
    this.label.setText(label);
  }

  public void setField(JTextComponent field) {
    Objects.requireNonNull(field);

    this.field = field;
  }

  public void setToolTipText(String tooltip) {
    field.setToolTipText(tooltip);
  }

  public void setText(String text) {
    field.setText(text);
  }

  public String getText() {
    return field.getText();
  }

  public void clear() {
    field.setText(StringUtils.EMPTY);
  }
}
