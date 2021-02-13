package com.aaroca.smtptester.ui.views;

import com.aaroca.smtptester.data.ResponseData;
import com.aaroca.smtptester.utils.Constants.Ui;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ResponseDialog extends JDialog {

  private JLabel status;
  private JLabel sessionProperties;
  private JLabel exception;

  public ResponseDialog(Frame owner) {
    super(owner, "Result", true);

    init();
    buildComponents();
    addComponents();
  }

  private void init() {
    setSize(300, 200);
    setLocationRelativeTo(getOwner());
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    setResizable(false);
  }

  private void buildComponents() {
    status = new JLabel();
    sessionProperties = new JLabel();
    exception = new JLabel();
  }

  private void addComponents() {
    add(status);
    add(sessionProperties);
    add(exception);
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

    if (response.getSession() != null) {
      sessionProperties.setText(response.getSession().getProperties().toString());
    }

    if (response.getException() != null) {
      exception.setText(ExceptionUtils.getStackTrace(response.getException()));
    }
  }
}
