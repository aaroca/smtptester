package com.aaroca.smtptester.ui.views;

import com.aaroca.smtptester.data.ResponseData;
import java.awt.Frame;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

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

    status.setText(response.getStatus());

    if (response.getSessionProperties() != null) {
      sessionProperties.setText(response.getSessionProperties().toString());
    }

    if (response.getException() != null) {
      exception.setText(response.getException().getMessage());
    }
  }
}
