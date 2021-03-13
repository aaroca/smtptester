package com.aaroca.smtptester.cli;

import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.utils.Constants.App;
import java.util.concurrent.Callable;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

@Command(mixinStandardHelpOptions = true, resourceBundle = App.DEFAULT_I18N_BASENAME)
public class MainCommand implements Callable<Integer> {

  @ArgGroup
  private EmailData emailData;

  @Override
  public Integer call() throws Exception {
    System.out.println("Hello " + getEmailData().getHost());
    return 0;
  }

  protected EmailData getEmailData() {
    return emailData;
  }
}
