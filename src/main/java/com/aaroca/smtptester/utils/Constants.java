package com.aaroca.smtptester.utils;

public final class Constants {

  private Constants() {
  }

  public static final class Ui {

    private Ui() {
    }

    public static final Integer DEFAULT_SEPARATION = 10;
    public static final Integer DEFAULT_TEXT_SIZE = 20;
    public static final Integer DEFAULT_ICON_SIZE = 20;
    public static final Integer SMALL_ICON_SIZE = 15;
    public static final Integer EXTRA_SMALL_ICON_SIZE = 10;
  }

  public static final class Mail {

    private Mail() {
    }

    public static final String DEFAULT_CONTENT = "test";
    public static final String HTML_MIMETYPE = "text/html";
    public static final Integer DEFAULT_PORT = 25;
    public static final Integer DEFAULT_SSL_PORT = 465;
    public static final Integer DEFAULT_TLS_PORT = 587;
  }
}
