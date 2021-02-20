package com.aaroca.smtptester.ui.views;

import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.data.ResponseData;
import com.aaroca.smtptester.services.I18nService;
import com.aaroca.smtptester.services.impl.DefaultI18nService;
import com.aaroca.smtptester.tasks.EmailSenderTask;
import com.aaroca.smtptester.ui.components.FileChooserField;
import com.aaroca.smtptester.ui.components.FormField;
import com.aaroca.smtptester.ui.components.SwitchableForm;
import com.aaroca.smtptester.utils.Constants.Mail;
import com.aaroca.smtptester.utils.Constants.Ui;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker.StateValue;
import org.apache.commons.lang3.StringUtils;

public class MainFrame extends JFrame implements ActionListener {

  private final I18nService i18nService;

  private JMenuBar mainMenu;
  private JMenu fileMenu;
  private JComboBox<Integer> timeoutComboBox;
  private JMenuItem exportMenuItem;
  private JMenuItem exitMenuItem;
  private JPanel panel;
  private ResponseDialog responseDialog;
  private PropertiesDialog propertiesDialog;
  private FormField host;
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
  private JCheckBox useTLS;
  private JCheckBox useSSL;
  private JButton sendEmail;
  private JButton clearForm;
  private JProgressBar progressBar;

  public MainFrame() {
    i18nService = DefaultI18nService.getInstance();

    init();
    buildComponents();
    addComponents();
  }

  @Override
  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == clearForm) {
      clear();
    } else if (event.getSource() == sendEmail) {
      runEmailTask();
    } else if (event.getSource() == useTLS) {
      useTLS();
    } else if (event.getSource() == useSSL) {
      useSSL();
    } else if (event.getSource() == exportMenuItem) {
      exportProperties();
    } else if (event.getSource() == exitMenuItem) {
      dispose();
    }
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    host.setEnabled(enabled);
    port.setEnabled(enabled);
    from.setEnabled(enabled);
    to.setEnabled(enabled);
    emailDetailsForm.setEnabled(enabled);
    authenticationForm.setEnabled(enabled);
    useTLS.setEnabled(enabled);
    useSSL.setEnabled(enabled);
    sendEmail.setEnabled(enabled);
    clearForm.setEnabled(enabled);
  }

  private void init() {
    setTitle(getI18nService().getString("main.title"));
    setSize(600, 800);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
    setResizable(false);
    addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent event) {
        host.requestFocus();
      }

      @Override
      public void windowActivated(WindowEvent event) {
        host.requestFocus();
      }
    });
  }

  private void buildComponents() {
    // Dialogs
    responseDialog = new ResponseDialog(this);
    propertiesDialog = new PropertiesDialog(this);

    // Menu
    mainMenu = new JMenuBar();
    fileMenu = new JMenu(getI18nService().getString("main.file"));
    timeoutComboBox = new JComboBox<>(Mail.TIMEOUT_OPTIONS);
    timeoutComboBox.setEditable(true);
    exportMenuItem = new JMenuItem(getI18nService().getString("main.export"));
    exportMenuItem.addActionListener(this);
    exitMenuItem = new JMenuItem(getI18nService().getString("main.exit"));
    exitMenuItem.addActionListener(this);

    fileMenu.add(exportMenuItem);
    fileMenu.addSeparator();
    fileMenu.add(exitMenuItem);
    mainMenu.add(fileMenu);
    mainMenu.add(new JLabel(getI18nService().getString("main.timeout")));
    mainMenu.add(timeoutComboBox);

    // Panel
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory
        .createEmptyBorder(Ui.DEFAULT_SEPARATION, Ui.DEFAULT_SEPARATION, Ui.DEFAULT_SEPARATION,
            Ui.DEFAULT_SEPARATION));

    // Basic details
    host = new FormField(getI18nService().getString("main.host"));
    port = new FormField(getI18nService().getString("main.port"));
    port.setText(Mail.DEFAULT_PORT.toString());
    from = new FormField(getI18nService().getString("main.from"));
    from.setText(Mail.DEFAULT_EMAIL);
    to = new FormField(getI18nService().getString("main.to"));
    to.setText(Mail.DEFAULT_EMAIL);
    to.setInfo(getI18nService().getString("main.to.tooltip"));

    // Email details form
    subject = new FormField(getI18nService().getString("main.subject"));
    body = new FormField(getI18nService().getString("main.body"), new JTextArea(4, 20));
    body.setText(Mail.DEFAULT_CONTENT);
    attachment = new FileChooserField(getI18nService().getString("main.attachment"));
    emailDetailsForm = new SwitchableForm(getI18nService().getString("main.details.check"),
        getI18nService().getString("main.details"),
        subject,
        body,
        attachment);

    // Authentication form
    username = new FormField(getI18nService().getString("main.username"));
    password = new FormField(getI18nService().getString("main.password"), new JPasswordField(20));
    authenticationForm = new SwitchableForm(getI18nService().getString("main.authentication.check"),
        getI18nService().getString("main.authentication"),
        username,
        password);

    // Use TLS form
    useTLS = new JCheckBox(getI18nService().getString("main.tls"));
    useTLS.addActionListener(this);

    // Use SSL form
    useSSL = new JCheckBox(getI18nService().getString("main.ssl"));
    useSSL.addActionListener(this);

    // Buttons
    sendEmail = new JButton(getI18nService().getString("main.send"));
    sendEmail.addActionListener(this);
    clearForm = new JButton(getI18nService().getString("main.clear"));
    clearForm.addActionListener(this);
    progressBar = new JProgressBar();
    progressBar.setIndeterminate(true);
    progressBar.setVisible(false);
    progressBar.setAlignmentX(Component.LEFT_ALIGNMENT);
  }

  private void addComponents() {
    // Menu
    setJMenuBar(mainMenu);

    // Panel
    add(panel);

    // Basic details
    panel.add(host);

    JPanel portOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
    portOptions.setAlignmentX(Component.LEFT_ALIGNMENT);
    portOptions.add(port);
    portOptions.add(useTLS);
    portOptions.add(useSSL);

    panel.add(portOptions);
    panel.add(from);
    panel.add(to);

    // Forms
    panel.add(emailDetailsForm);
    panel.add(authenticationForm);

    // Buttons
    JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    buttonsPanel.add(sendEmail);
    buttonsPanel.add(clearForm);

    panel.add(buttonsPanel);
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

  private void clear() {
    timeoutComboBox.setSelectedIndex(0);
    host.clear();
    port.setText(Mail.DEFAULT_PORT.toString());
    useTLS.setSelected(false);
    useSSL.setSelected(false);
    to.setText(Mail.DEFAULT_EMAIL);
    from.setText(Mail.DEFAULT_EMAIL);
    emailDetailsForm.clear();
    subject.clear();
    body.setText(Mail.DEFAULT_CONTENT);
    attachment.clear();
    authenticationForm.clear();
    username.clear();
    password.clear();
  }

  private void runEmailTask() {
    displayProgressBar();

    EmailSenderTask task = new EmailSenderTask(collectEmailData());
    task.addPropertyChangeListener(taskEvent -> {
      if (StringUtils.equals(taskEvent.getPropertyName(), "state")
          && StateValue.DONE.equals(taskEvent.getNewValue())) {
        try {
          displayResponse(task.get());
        } catch (InterruptedException | ExecutionException exception) {
          ResponseData error = new ResponseData(false, exception.getLocalizedMessage());
          error.setException(exception);
          displayResponse(error);
        }
      }
    });
    task.execute();
  }

  private void displayResponse(ResponseData responseData) {
    hideProgressBar();

    responseDialog.setResponseData(responseData);
    responseDialog.pack();
    responseDialog.setVisible(true);
  }

  private void useSSL() {
    if (useSSL.isSelected()) {
      port.setText(Mail.DEFAULT_SSL_PORT.toString());
    }

    useTLS.setSelected(false);
  }

  private void useTLS() {
    if (useTLS.isSelected()) {
      port.setText(Mail.DEFAULT_TLS_PORT.toString());
    }

    useSSL.setSelected(false);
  }

  private void exportProperties() {
    propertiesDialog.exportProperties(collectEmailData());
    propertiesDialog.pack();
    propertiesDialog.setVisible(true);
  }

  private EmailData collectEmailData() {
    EmailData email = new EmailData();
    email.setHost(host.getText());
    email.setPort(port.getText());
    email.setTo(to.getText());
    email.setFrom(from.getText());
    email.setDetailedMessage(emailDetailsForm.isSelected());
    email.setSubject(subject.getText());
    email.setBody(body.getText());
    email.setAttachment(attachment.getFile());
    email.setUseAuthentication(authenticationForm.isSelected());
    email.setUsername(username.getText());
    email.setPassword(password.getText());
    email.setUseTLS(useTLS.isSelected());
    email.setUseSSL(useSSL.isSelected());
    email.setTimeout(getTimeout());

    return email;
  }

  private Integer getTimeout() {
    return timeoutComboBox.getSelectedItem() instanceof Integer
        ? (Integer) timeoutComboBox.getSelectedItem()
        : Mail.DEFAULT_TIMEOUT;
  }

  protected I18nService getI18nService() {
    return i18nService;
  }
}
