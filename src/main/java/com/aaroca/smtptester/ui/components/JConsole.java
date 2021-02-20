package com.aaroca.smtptester.ui.components;

import com.aaroca.smtptester.services.I18nService;
import com.aaroca.smtptester.services.impl.DefaultI18nService;
import com.aaroca.smtptester.utils.logging.JConsoleLoggingHandler;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class JConsole extends JScrollPane {

  private final JTextArea logArea;
  private final JConsoleLoggingHandler loggingHandler;
  private final I18nService i18nService;

  public JConsole() {
    this.i18nService = DefaultI18nService.getInstance();

    this.logArea = new JTextArea(10, 20);
    this.logArea.setEditable(false);
    this.logArea.setBackground(Color.BLACK);
    this.loggingHandler = new JConsoleLoggingHandler(this.logArea);

    setViewportView(this.logArea);
    setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    setBorder(BorderFactory.createTitledBorder(getI18nService().getString("main.trace")));
  }

  public void clear() {
    getLoggingHandler().flush();
  }

  protected I18nService getI18nService() {
    return i18nService;
  }

  public JConsoleLoggingHandler getLoggingHandler() {
    return loggingHandler;
  }
}
