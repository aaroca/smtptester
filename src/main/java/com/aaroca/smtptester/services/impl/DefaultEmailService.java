package com.aaroca.smtptester.services.impl;

import com.aaroca.smtptester.converters.Converter;
import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.data.ResponseData;
import com.aaroca.smtptester.services.EmailService;
import com.aaroca.smtptester.services.I18nService;
import com.aaroca.smtptester.utils.Constants.Mail;
import com.aaroca.smtptester.utils.streams.MailDebugStream;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;

public class DefaultEmailService implements EmailService {

  private final Converter<EmailData, Properties> propertiesConverter;
  private final Converter<EmailData, Authenticator> authenticatorConverter;
  private final I18nService i18nService;
  private final MailDebugStream debugStream;

  public DefaultEmailService(Converter<EmailData, Properties> propertiesConverter,
      Converter<EmailData, Authenticator> authenticatorConverter) {
    this.propertiesConverter = propertiesConverter;
    this.authenticatorConverter = authenticatorConverter;
    this.i18nService = DefaultI18nService.getInstance();
    this.debugStream = new MailDebugStream();
  }

  @Override
  public ResponseData send(EmailData email) {
    ResponseData response = null;

    Session session = createSession(email);
    session.setDebugOut(getDebugStream());

    try {
      Message message = createMessage(email, session);

      Transport.send(message);

      response = new ResponseData(true, getI18nService().getString("response.success"));
    } catch (MessagingException exception) {
      response = new ResponseData(false, exception.getLocalizedMessage());
      response.setException(exception);
    }

    return response;
  }

  private Session createSession(EmailData email) {
    Properties emailProperties = getPropertiesConverter().convert(email);
    Authenticator authenticator = getAuthenticatorConverter().convert(email);

    return Session.getInstance(emailProperties, authenticator);
  }

  private Message createMessage(EmailData email, Session session) throws MessagingException {
    Message message = new MimeMessage(session);

    message.setFrom(new InternetAddress(email.getFrom()));
    message.setRecipients(RecipientType.TO, InternetAddress.parse(email.getTo()));
    message.setContent(StringUtils.defaultString(email.getBody(), Mail.DEFAULT_CONTENT),
        Mail.HTML_MIMETYPE);

    if (email.isDetailedMessage()) {
      message.setSubject(email.getSubject());
    }

    return message;
  }

  protected Converter<EmailData, Properties> getPropertiesConverter() {
    return propertiesConverter;
  }

  protected Converter<EmailData, Authenticator> getAuthenticatorConverter() {
    return authenticatorConverter;
  }

  protected I18nService getI18nService() {
    return i18nService;
  }

  protected MailDebugStream getDebugStream() {
    return debugStream;
  }
}
