package com.aaroca.smtptester.services.impl;

import com.aaroca.smtptester.services.I18nService;
import com.aaroca.smtptester.utils.Constants.App;
import java.util.ResourceBundle;

public class DefaultI18nService implements I18nService {

  private static I18nService INSTANCE;

  private ResourceBundle messages;

  private DefaultI18nService() {
    messages = ResourceBundle.getBundle(App.DEFAULT_I18N_BASENAME);
  }

  public static I18nService getInstance() {
    if (INSTANCE == null) {
      INSTANCE = new DefaultI18nService();
    }

    return INSTANCE;
  }

  @Override
  public String getString(String key) {
    return getMessages().getString(key);
  }

  protected ResourceBundle getMessages() {
    return messages;
  }
}
