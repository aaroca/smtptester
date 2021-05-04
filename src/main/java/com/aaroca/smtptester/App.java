package com.aaroca.smtptester;

import com.aaroca.smtptester.cli.MainCommand;
import com.aaroca.smtptester.ui.views.MainFrame;
import com.aaroca.smtptester.utils.Constants.Ui;
import com.formdev.flatlaf.FlatDarculaLaf;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.swing.IconFontSwing;
import org.apache.commons.lang3.ArrayUtils;
import picocli.CommandLine;

public class App {

  public static void main(String[] args) {
    if (ArrayUtils.isNotEmpty(args)) {
      new App().run(args);
    } else {
      new App().start();
    }
  }

  private void start() {
    // Customize UI
    FlatDarculaLaf.install();
    IconFontSwing.register(FontAwesome.getIconFont());

    // Display UI
    SwingUtilities.invokeLater(() -> {
      JFrame mainFrame = new MainFrame();
      mainFrame.setIconImage(IconFontSwing.buildImage(FontAwesome.ENVELOPE, Ui.DEFAULT_ICON_SIZE));
      mainFrame.pack();
      mainFrame.setVisible(true);
    });
  }

  private void run(String[] args) {
    int exitCode = new CommandLine(new MainCommand()).execute(args);
    System.exit(exitCode);
  }
}
