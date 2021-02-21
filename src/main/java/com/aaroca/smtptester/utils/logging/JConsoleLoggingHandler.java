package com.aaroca.smtptester.utils.logging;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import javax.swing.JTextArea;
import org.apache.commons.lang3.StringUtils;

public class JConsoleLoggingHandler extends Handler {

  private static final String MESSAGE_FORMAT = "- [%s] %s \r\n";

  private final JTextArea logArea;
  private StringBuilder logTrace;

  public JConsoleLoggingHandler(JTextArea logArea) {
    this.logArea = logArea;
    flush();
  }

  @Override
  public void publish(LogRecord record) {
    String date = record.getInstant().atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));

    getLogTrace().append(String.format(MESSAGE_FORMAT, date, record.getMessage()));
    getLogArea().setText(getLogTrace().toString());
    getLogArea().setCaretPosition(getLogArea().getDocument().getLength());
  }

  @Override
  public void flush() {
    this.logTrace = new StringBuilder();
    getLogArea().setText(StringUtils.EMPTY);
  }

  @Override
  public void close() throws SecurityException {
    // Nothing to do
  }

  protected JTextArea getLogArea() {
    return logArea;
  }

  protected StringBuilder getLogTrace() {
    return logTrace;
  }
}
