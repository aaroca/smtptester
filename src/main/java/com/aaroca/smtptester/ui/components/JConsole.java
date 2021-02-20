package com.aaroca.smtptester.ui.components;

import com.aaroca.smtptester.utils.logging.JConsoleLoggingHandler;
import javax.swing.JTextArea;

public class JConsole extends JTextArea {

  private final JConsoleLoggingHandler loggingHandler;

  public JConsole(int rows, int columns) {
    super(rows, columns);
    loggingHandler = new JConsoleLoggingHandler(this);
  }

  public JConsoleLoggingHandler getLoggingHandler() {
    return loggingHandler;
  }
}
