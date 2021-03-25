package com.aaroca.smtptester.cli.providers;

import com.aaroca.smtptester.services.PropertiesService;
import com.aaroca.smtptester.services.impl.DefaultPropertiesService;
import com.aaroca.smtptester.utils.Constants.App.Properties;
import picocli.CommandLine.IVersionProvider;

public class MavenVersionProvider implements IVersionProvider {

  private final PropertiesService propertiesService;

  public MavenVersionProvider() {
    this.propertiesService = DefaultPropertiesService.getInstance();
  }

  @Override
  public String[] getVersion() {
    return new String[]{getPropertiesService().getProperty(Properties.VERSION)};
  }

  protected PropertiesService getPropertiesService() {
    return propertiesService;
  }
}
