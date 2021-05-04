package com.aaroca.smtptester.converters.impl;

import com.aaroca.smtptester.converters.Converter;
import com.aaroca.smtptester.data.EmailData;
import java.util.Objects;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class EmailAuthenticationConverter implements Converter<EmailData, Authenticator> {

  private static Converter<EmailData, Authenticator> INSTANCE;

  private EmailAuthenticationConverter() {

  }

  public static Converter<EmailData, Authenticator> getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new EmailAuthenticationConverter();
    }

    return INSTANCE;
  }

  @Override
  public Authenticator convert(EmailData emailData) {
    Objects.requireNonNull(emailData);

    Authenticator authenticator = null;

    if (emailData.isUsingAuthentication()) {
      authenticator = new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(emailData.getUsername(), emailData.getPassword());
        }
      };
    }

    return authenticator;
  }
}
