package com.aaroca.smtptester.ui.views;

import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.ui.components.FileChooserField;
import com.aaroca.smtptester.ui.components.FormField;
import com.aaroca.smtptester.ui.components.SwitchableForm;
import com.aaroca.smtptester.utils.Constants.Mail;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

public class MainFrame extends JFrame {

  private FormField server;
  private FormField port;
  private FormField to;
  private FormField from;
  private SwitchableForm emailDetailsForm;
  private FormField subject;
  private FormField body;
  private FileChooserField attachment;
  private SwitchableForm authenticationForm;
  private FormField username;
  private FormField password;
  private SwitchableForm tlsForm;
  private FormField tlsPort;
  private SwitchableForm sslForm;
  private FormField sslPort;
  private JButton sendEmail;
  private JButton clearForm;

  public MainFrame() {
    init();
    addComponents();
  }

  private void init() {
    setTitle("smtptester");
    setSize(600, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    setResizable(false);
  }

  private void addComponents() {
    addConnectionInformationFields();
    addEmailFields();
    addAuthenticationFields();
    addTlsFields();
    addSslFields();
    addButtons();
  }

  private void addConnectionInformationFields() {
    server = new FormField("Server");
    port = new FormField("Port");

    add(server);
    add(port);
  }

  private void addEmailFields() {
    from = new FormField("From");
    to = new FormField("To");
    to.setToolTipText("Separate with ; for sending several emails");

    add(from);
    add(to);

    subject = new FormField("Subject");
    body = new FormField("Body", new JTextArea(4, 20));
    attachment = new FileChooserField("Attachment");
    emailDetailsForm = new SwitchableForm("Set email details",
        "Email details",
        subject,
        body,
        attachment);
    add(emailDetailsForm);
  }

  private void addAuthenticationFields() {
    username = new FormField("Username");
    password = new FormField("Password", new JPasswordField(20));

    authenticationForm = new SwitchableForm("Use authentication",
        "Authentication",
        username,
        password);

    add(authenticationForm);
  }

  private void addTlsFields() {
    tlsPort = new FormField("Port");
    tlsPort.setText(Mail.DEFAULT_TLS_PORT.toString());
    tlsForm = new SwitchableForm("Use TLS", "TLS", tlsPort);

    add(tlsForm);
  }

  private void addSslFields() {
    sslPort = new FormField("Port");
    sslPort.setText(Mail.DEFAULT_SSL_PORT.toString());
    sslForm = new SwitchableForm("Use SSL", "SSL", sslPort);

    add(sslForm);
  }

  private void addButtons() {
    sendEmail = new JButton("Send");
    sendEmail.addActionListener(new SendEmailActionListener(this));
    clearForm = new JButton("Clear");
    clearForm.addActionListener(new ClearFormActionListener());

    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    buttonsPanel.add(sendEmail);
    buttonsPanel.add(clearForm);

    add(buttonsPanel);
  }

  private class ClearFormActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent event) {
      server.clear();
      port.clear();
      to.clear();
      from.clear();
      emailDetailsForm.clear();
      subject.clear();
      body.clear();
      attachment.clear();
      authenticationForm.clear();
      username.clear();
      password.clear();
      tlsForm.clear();
      tlsPort.setText(Mail.DEFAULT_TLS_PORT.toString());
      sslForm.clear();
      sslPort.setText(Mail.DEFAULT_SSL_PORT.toString());
    }
  }

  private class SendEmailActionListener implements ActionListener {

    private ResultDialog result;

    public SendEmailActionListener(Frame owner) {
      result = new ResultDialog(owner);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      result.pack();
      result.setVisible(true);
    }

    private EmailData collectEmailData() {
      return new EmailData();
    }
  }
}
