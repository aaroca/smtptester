package com.aaroca.smtptester.ui.views;

import com.aaroca.smtptester.data.ResponseData;
import com.aaroca.smtptester.services.I18nService;
import com.aaroca.smtptester.services.impl.DefaultI18nService;
import com.aaroca.smtptester.utils.Constants.Ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ResponseDialog extends JDialog {

  private final I18nService i18nService;

  private JPanel panel;
  private JLabel status;
  private JTextArea exception;

  public ResponseDialog(Frame owner) {
    super(owner, "", true);

    i18nService = DefaultI18nService.getInstance();

    init();
    buildComponents();
    addComponents();
  }

  private void init() {
    setTitle(getI18nService().getString("response.title"));
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
    status = new JLabel();
    status.setAlignmentX(Component.LEFT_ALIGNMENT);
    status.setIconTextGap(Ui.DEFAULT_SEPARATION);
    exception = new JTextArea();
    exception
        .setBorder(BorderFactory.createTitledBorder(getI18nService().getString("response.trace")));
    exception.setAlignmentX(Component.LEFT_ALIGNMENT);
    exception.setEditable(false);
    exception.setRows(5);
    exception.setAutoscrolls(true);
  }

  private void addComponents() {
    add(panel);
    panel.add(status);
    panel.add(exception);
  }

  public void setResponseData(ResponseData response) {
    Objects.requireNonNull(response);

    Image image;
    Icon icon;
    if (response.isSuccess()) {
      image = IconFontSwing.buildImage(FontAwesome.CHECK_CIRCLE, Ui.DEFAULT_ICON_SIZE, Color.GREEN);
      icon = IconFontSwing.buildIcon(FontAwesome.CHECK_CIRCLE, Ui.DEFAULT_ICON_SIZE, Color.GREEN);
    } else {
      image = IconFontSwing
          .buildImage(FontAwesome.EXCLAMATION_CIRCLE, Ui.DEFAULT_ICON_SIZE, Color.RED);
      icon = IconFontSwing
          .buildIcon(FontAwesome.EXCLAMATION_CIRCLE, Ui.DEFAULT_ICON_SIZE, Color.RED);
    }

    setIconImage(image);
    status.setIcon(icon);
    status.setText(response.getStatus());

    if (response.getException() != null) {
      exception.setVisible(true);
      exception.setText(ExceptionUtils.getStackTrace(response.getException()));
    } else {
      exception.setVisible(false);
    }
  }

  protected I18nService getI18nService() {
    return i18nService;
  }
}
