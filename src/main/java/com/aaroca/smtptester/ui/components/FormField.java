package com.aaroca.smtptester.ui.components;

import static com.aaroca.smtptester.utils.Constants.Ui.DEFAULT_TEXT_SIZE;

import com.aaroca.smtptester.utils.Constants.Ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.Objects;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.apache.commons.lang3.StringUtils;

public class FormField extends JComponent {

  private JLabel label;
  private JTextComponent field;
  private JLabel info;

  public FormField(String label) {
    this(label, new JTextField(DEFAULT_TEXT_SIZE));
  }

  public FormField(String label, JTextComponent field) {
    init();
    buildComponents(label, field);
    addComponents();
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);
    label.setEnabled(enabled);
    field.setEnabled(enabled);
  }

  public void clear() {
    field.setText(StringUtils.EMPTY);
  }

  private void init() {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    setAlignmentX(Component.LEFT_ALIGNMENT);
  }

  private void buildComponents(String label, JTextComponent field) {
    Objects.requireNonNull(field);

    this.label = new JLabel(label);
    this.field = field;
  }

  private void addComponents() {
    if (StringUtils.isNotEmpty(label.getText())) {
      add(label);
    }

    add(field);
  }

  @Override
  public void setToolTipText(String tooltip) {
    field.setToolTipText(tooltip);
  }

  public void setInfo(String info) {
    if (StringUtils.isNotEmpty(info)) {
      if (this.info == null) {
        this.info = new JLabel(
            IconFontSwing.buildIcon(FontAwesome.INFO_CIRCLE, Ui.SMALL_ICON_SIZE, Color.white));
        add(this.info);
      }

      this.info.setToolTipText(info);
    } else if (this.info.isVisible()) {
      remove(this.info);
      this.info = null;
    }
  }

  public void setText(String text) {
    field.setText(text);
  }

  public String getText() {
    return field.getText();
  }
}
