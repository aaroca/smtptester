package com.aaroca.smtptester.utils;

import java.awt.Color;

public final class Constants {

  private Constants() {
  }

  public static final class App {

    private App() {
    }

    public static final String DEFAULT_I18N_BASENAME = "i18n/messages";
  }

  public static final class Ui {

    private Ui() {
    }

    public static final Integer DEFAULT_SEPARATION = 10;
    public static final Integer DEFAULT_TEXT_SIZE = 20;
    public static final Integer DEFAULT_ICON_SIZE = 20;
    public static final Integer SMALL_ICON_SIZE = 15;
    public static final Integer EXTRA_SMALL_ICON_SIZE = 10;
    public static final Color BUTTON_COLOR = new Color(173, 173, 173);
  }

  public static final class Mail {

    private Mail() {
    }

    public static final String DEFAULT_CONTENT = "test";
    public static final String HTML_MIMETYPE = "text/html;charset=UTF-8";
    public static final String DEFAULT_EMAIL = "test@mail.com";
    public static final Integer DEFAULT_PORT = 25;
    public static final Integer DEFAULT_SSL_PORT = 465;
    public static final Integer DEFAULT_TLS_PORT = 587;
    public static final Integer DEFAULT_TIMEOUT = 1000;
    public static final Integer[] TIMEOUT_OPTIONS = new Integer[] {DEFAULT_TIMEOUT, 1500, 2000};
  }
}
