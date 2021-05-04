package com.aaroca.smtptester.services.impl;

import com.aaroca.smtptester.services.PropertiesService;
import com.aaroca.smtptester.utils.Constants.App;
import java.io.IOException;
import java.util.Properties;

public class DefaultPropertiesService implements PropertiesService {

  private static PropertiesService INSTANCE;

  private Properties properties;

  private DefaultPropertiesService() {
    properties = new Properties();

    try {
      properties.load(getClass().getClassLoader().getResourceAsStream(App.APP_PROPERTIES));
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public static PropertiesService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new DefaultPropertiesService();
    }

    return INSTANCE;
  }

  @Override
  public String getProperty(String key) {
    return getProperties().getProperty(key);
  }

  protected Properties getProperties() {
    return properties;
  }
}
