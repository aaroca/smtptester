package com.aaroca.smtptester.tasks;

import com.aaroca.smtptester.converters.impl.EmailPropertiesConverter;
import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.data.ResponseData;
import com.aaroca.smtptester.services.EmailService;
import com.aaroca.smtptester.services.impl.DefaultEmailService;
import javax.swing.SwingWorker;

public class EmailSenderTask extends SwingWorker<ResponseData, String> {

  private final EmailService emailService;
  private final EmailData email;

  public EmailSenderTask(EmailData email) {
    this.email = email;
    this.emailService = new DefaultEmailService(new EmailPropertiesConverter());
  }

  @Override
  protected ResponseData doInBackground() {
    return getEmailService().send(getEmail());
  }

  protected EmailService getEmailService() {
    return emailService;
  }

  protected EmailData getEmail() {
    return email;
  }
}
