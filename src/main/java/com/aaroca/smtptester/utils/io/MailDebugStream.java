package com.aaroca.smtptester.utils.io;

import static java.util.logging.Level.INFO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.logging.Logger;

public class MailDebugStream extends PrintStream {

  private static final Logger LOG = Logger.getLogger(MailDebugStream.class.getName());

  public MailDebugStream() {
    super(new ByteArrayOutputStream());
  }

  @Override
  public void println(String message) {
    LOG.log(INFO, message);
  }
}
