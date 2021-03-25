package com.aaroca.smtptester.cli;

import com.aaroca.smtptester.cli.providers.MavenVersionProvider;
import com.aaroca.smtptester.data.CommandData;
import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.utils.Constants.App;
import java.util.concurrent.Callable;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

@Command(mixinStandardHelpOptions = true,
    resourceBundle = App.DEFAULT_I18N_BASENAME,
    versionProvider = MavenVersionProvider.class)
public class MainCommand implements Callable<Integer> {

  @ArgGroup
  private CommandData commandData;

  @Override
  public Integer call() {
    System.out.println("Hello " + getCommandData().getHost());
    return 0;
  }

  protected EmailData getCommandData() {
    return commandData;
  }
}
