package com.aaroca.smtptester.ui.views;

import com.aaroca.smtptester.data.ResultingData;
import java.awt.Frame;
import java.util.Objects;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class ResultDialog extends JDialog {

  private JLabel status;
  private JLabel exception;

  public ResultDialog(Frame owner) {
    super(owner, "Result", true);

    init();
    addComponents();
  }

  private void init() {
    setSize(300, 200);
    setLocationRelativeTo(getOwner());
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    setResizable(false);
  }

  private void addComponents() {
    status = new JLabel();
    exception = new JLabel();

    add(status);
    add(exception);
  }

  public void setResultingData(ResultingData results) {
    Objects.requireNonNull(results);

    status.setText(results.getStatus());

    if (results.getException() != null) {
      exception.setText(results.getException().getMessage());
    }
  }
}
