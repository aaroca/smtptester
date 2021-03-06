package com.aaroca.smtptester.converters.impl;

import com.aaroca.smtptester.converters.Converter;
import com.aaroca.smtptester.data.EmailData;
import java.util.Objects;
import java.util.Properties;

public class EmailPropertiesConverter implements Converter<EmailData, Properties> {

  private static Converter<EmailData, Properties> INSTANCE;
  
  private EmailPropertiesConverter() {

  }

  public static Converter<EmailData, Properties> getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new EmailPropertiesConverter();
    }

    return INSTANCE;
  }

  @Override
  public Properties convert(EmailData emailData) {
    Objects.requireNonNull(emailData);

    Properties properties = new Properties();
    properties.put("mail.smtp.host", emailData.getHost());
    properties.put("mail.smtp.port", emailData.getPort());

    if (emailData.isUsingAuthentication()) {
      properties.put("mail.smtp.auth", "true");
    }

    if (emailData.isUsingSSL()) {
      properties.setProperty("mail.smtp.ssl.enable", "true");
    } else if (emailData.isUsingTLS()) {
      properties.put("mail.smtp.starttls.enable", "true");
    }

    properties.put("mail.smtp.connectiontimeout", emailData.getTimeout());
    properties.put("mail.smtp.timeout", emailData.getTimeout());

    if (emailData.isDebugEnabled()) {
      properties.put("mail.debug", "true");
    }

    return properties;
  }
}
