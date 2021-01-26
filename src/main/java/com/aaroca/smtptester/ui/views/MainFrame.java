package com.aaroca.smtptester.ui.views;

import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.tasks.EmailSenderTask;
import com.aaroca.smtptester.ui.components.FileChooserField;
import com.aaroca.smtptester.ui.components.FormField;
import com.aaroca.smtptester.ui.components.SwitchableForm;
import com.aaroca.smtptester.utils.Constants.Mail;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker.StateValue;
import org.apache.commons.lang3.StringUtils;

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
  private JProgressBar progressBar;

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

    progressBar = new JProgressBar();
    progressBar.setIndeterminate(true);
    progressBar.setVisible(false);
    progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);

    add(buttonsPanel);
    add(progressBar);
  }

  private void displayProgressBar() {
    setEnabled(false);
    progressBar.setVisible(true);
  }

  private void hideProgressBar() {
    setEnabled(true);
    progressBar.setVisible(false);
  }

  @Override
  public void setEnabled(boolean enabled) {
    subject.setEnabled(enabled);

    server.setEnabled(enabled);
    port.setEnabled(enabled);
    from.setEnabled(enabled);
    to.setEnabled(enabled);
    emailDetailsForm.setEnabled(enabled);
    authenticationForm.setEnabled(enabled);
    tlsForm.setEnabled(enabled);
    sslForm.setEnabled(enabled);

    sendEmail.setEnabled(enabled);
    clearForm.setEnabled(enabled);
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

    private static final char SEPARATION_CHAR = ';';

    private ResultDialog dialog;

    public SendEmailActionListener(Frame owner) {
      dialog = new ResultDialog(owner);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
      displayProgressBar();

      EmailSenderTask task = new EmailSenderTask(collectEmailData());
      task.addPropertyChangeListener(taskEvent -> {
        if (StringUtils.equals(taskEvent.getPropertyName(), "state")
            && StateValue.DONE.equals(taskEvent.getNewValue())) {
          try {
            hideProgressBar();

            dialog.setResultingData(task.get());
            dialog.pack();
            dialog.setVisible(true);
          } catch (InterruptedException e) {
            e.printStackTrace();
          } catch (ExecutionException e) {
            e.printStackTrace();
          }
        }
      });
      task.execute();
    }

    private EmailData collectEmailData() {
      EmailData email = new EmailData();
      email.setServer(server.getText());
      email.setPort(port.getText());
      email.setTo(getEmailList(to.getText()));
      email.setFrom(from.getText());
      email.setDetailedMessage(emailDetailsForm.isSelected());
      email.setSubject(subject.getText());
      email.setBody(body.getText());
      email.setAttachment(attachment.getFile());
      email.setUseAuthentication(authenticationForm.isSelected());
      email.setUsername(username.getText());
      email.setPassword(password.getText());
      email.setUseTLS(tlsForm.isSelected());
      email.setTlsPort(tlsPort.getText());
      email.setUseSSL(sslForm.isSelected());
      email.setSslPort(sslPort.getText());

      return email;
    }

    private List<String> getEmailList(String to) {
      return Arrays.stream(StringUtils.split(to, SEPARATION_CHAR)).collect(Collectors.toList());
    }
  }
}
