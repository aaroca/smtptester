package com.aaroca.smtptester.ui.views;

import com.aaroca.smtptester.converters.Converter;
import com.aaroca.smtptester.converters.impl.EmailPropertiesConverter;
import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.services.I18nService;
import com.aaroca.smtptester.services.impl.DefaultI18nService;
import com.aaroca.smtptester.utils.Constants.Ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.Properties;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;

public class PropertiesDialog extends JDialog implements ActionListener {

  private final Converter<EmailData, Properties> propertiesConverter;

  private JPanel panel;
  private JTextArea properties;
  private JButton clipboardButton;
  private Icon clipboardFeedback;

  public PropertiesDialog(Frame owner) {
    super(owner, "", true);

    propertiesConverter = new EmailPropertiesConverter();

    init();
    buildComponents();
    addComponents();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == clipboardButton) {
      copyPropertiesToClipboard();
    }
  }

  @Override
  public void dispose() {
    clipboardButton.setIcon(null);
    super.dispose();
  }

  private void init() {
    setTitle(getI18nService().getString("export.title"));
    setSize(300, 200);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    setResizable(false);
  }

  private void buildComponents() {
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(
        Ui.DEFAULT_SEPARATION, Ui.DEFAULT_SEPARATION, Ui.DEFAULT_SEPARATION,
        Ui.DEFAULT_SEPARATION));
    panel.setAlignmentX(Component.LEFT_ALIGNMENT);
    properties = new JTextArea(5, Ui.DEFAULT_TEXT_SIZE);
    properties.setEditable(false);
    properties.setAutoscrolls(true);
    clipboardButton = new JButton(getI18nService().getString("export.clipboard"));
    clipboardButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    clipboardButton.addActionListener(this);
    clipboardFeedback = IconFontSwing
        .buildIcon(FontAwesome.CHECK_CIRCLE, Ui.EXTRA_SMALL_ICON_SIZE, Color.GREEN);
  }

  private void addComponents() {
    add(panel);
    panel.add(properties);
    panel.add(clipboardButton);
  }

  public void exportProperties(EmailData emailData) {
    Objects.requireNonNull(emailData);

    Properties extractedProperties = getPropertiesConverter().convert(emailData);

    properties.setText(formatProperties(extractedProperties));
  }

  private String formatProperties(Properties properties) {
    StringBuilder formattedProperties = new StringBuilder();

    if (properties != null && !properties.isEmpty()) {
      properties.keySet().stream()
          .map(key -> key + "=" + properties.get(key) + "\r\n")
          .forEach(formattedProperties::append);
    } else {
      formattedProperties.append(getI18nService().getString("export.empty"));
    }

    return formattedProperties.toString();
  }

  private void copyPropertiesToClipboard() {
    String formattedProperties = properties.getText();

    Toolkit.getDefaultToolkit().getSystemClipboard()
        .setContents(new StringSelection(formattedProperties), null);

    clipboardButton.setIcon(clipboardFeedback);
  }

  protected Converter<EmailData, Properties> getPropertiesConverter() {
    return propertiesConverter;
  }

  protected I18nService getI18nService() {
    return DefaultI18nService.getInstance();
  }
}
