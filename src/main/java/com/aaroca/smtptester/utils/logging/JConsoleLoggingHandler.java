package com.aaroca.smtptester.utils.logging;

import com.aaroca.smtptester.ui.components.JConsole;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class JConsoleLoggingHandler extends Handler {

  private final JConsole logArea;
  private StringBuilder logTrace;

  public JConsoleLoggingHandler(JConsole logArea) {
    this.logArea = logArea;
    flush();
  }

  @Override
  public void publish(LogRecord record) {
    getLogTrace().append("- " + record.getMessage() + "\r\n");
    getLogArea().setText(getLogTrace().toString());
  }

  @Override
  public void flush() {
    this.logTrace = new StringBuilder();
  }

  @Override
  public void close() throws SecurityException {
    // Nothing to do
  }

  protected JConsole getLogArea() {
    return logArea;
  }

  protected StringBuilder getLogTrace() {
    return logTrace;
  }
}
