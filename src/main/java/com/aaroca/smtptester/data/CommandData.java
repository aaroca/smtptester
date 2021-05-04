package com.aaroca.smtptester.data;

import picocli.CommandLine.Option;

public class CommandData extends EmailData {

  @Option(names = {"-e", "--export"},
      descriptionKey = "main.export.help")
  private boolean export;

  public boolean shouldExport() {
    return export;
  }

  public void setExport(boolean export) {
    this.export = export;
  }
}
