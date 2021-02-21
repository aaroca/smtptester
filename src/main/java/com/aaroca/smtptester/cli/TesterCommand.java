package com.aaroca.smtptester.cli;

import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command
public class TesterCommand implements Callable<Integer> {

  @Parameters(index = "0", defaultValue = "Text to display")
  private String text;

  @Override
  public Integer call() throws Exception {
    System.out.println("Hello " + text);
    return 0;
  }
}
