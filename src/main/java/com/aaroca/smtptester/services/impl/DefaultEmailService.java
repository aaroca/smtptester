package com.aaroca.smtptester.services.impl;

import com.aaroca.smtptester.converters.Converter;
import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.data.ResponseData;
import com.aaroca.smtptester.services.EmailService;
import java.util.Properties;

public class DefaultEmailService implements EmailService {

  private final Converter<EmailData, Properties> propertiesConverter;

  public DefaultEmailService(Converter<EmailData, Properties> propertiesConverter) {
    this.propertiesConverter = propertiesConverter;
  }

  @Override
  public ResponseData send(EmailData email) {
    Properties emailProperties = getPropertiesConverter().convert(email);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException exception) {
      exception.printStackTrace();
    }

    ResponseData response = new ResponseData(true, "Status");
    response.setException(new IllegalArgumentException("Exception message"));
    response.setSessionProperties(emailProperties);

    return response;
  }

  protected Converter<EmailData, Properties> getPropertiesConverter() {
    return propertiesConverter;
  }
}
