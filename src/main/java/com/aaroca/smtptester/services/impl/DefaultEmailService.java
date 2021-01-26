package com.aaroca.smtptester.services.impl;

import com.aaroca.smtptester.converters.Converter;
import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.data.ResultingData;
import com.aaroca.smtptester.services.EmailService;
import java.util.Properties;

public class DefaultEmailService implements EmailService {

  private final Converter<EmailData, Properties> propertiesConverter;

  public DefaultEmailService(Converter<EmailData, Properties> propertiesConverter) {
    this.propertiesConverter = propertiesConverter;
  }

  @Override
  public ResultingData send(EmailData email) {
    Properties emailProperties = getPropertiesConverter().convert(email);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return new ResultingData(true, "Status");
  }

  protected Converter<EmailData, Properties> getPropertiesConverter() {
    return propertiesConverter;
  }
}
