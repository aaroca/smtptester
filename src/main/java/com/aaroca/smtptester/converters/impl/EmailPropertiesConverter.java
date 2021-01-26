package com.aaroca.smtptester.converters.impl;

import com.aaroca.smtptester.converters.Converter;
import com.aaroca.smtptester.data.EmailData;
import java.util.Objects;
import java.util.Properties;

public class EmailPropertiesConverter implements Converter<EmailData, Properties> {

  @Override
  public Properties convert(EmailData emailData) {
    Objects.requireNonNull(emailData);

    Properties target = new Properties();


    return target;
  }
}
