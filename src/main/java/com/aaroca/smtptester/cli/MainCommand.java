package com.aaroca.smtptester.cli;

import com.aaroca.smtptester.cli.providers.MavenVersionProvider;
import com.aaroca.smtptester.converters.Converter;
import com.aaroca.smtptester.converters.impl.EmailPropertiesConverter;
import com.aaroca.smtptester.data.CommandData;
import com.aaroca.smtptester.data.EmailData;
import com.aaroca.smtptester.data.ResponseData;
import com.aaroca.smtptester.services.EmailService;
import com.aaroca.smtptester.services.I18nService;
import com.aaroca.smtptester.services.impl.DefaultEmailService;
import com.aaroca.smtptester.services.impl.DefaultI18nService;
import com.aaroca.smtptester.utils.Constants.App;
import java.util.Properties;
import java.util.concurrent.Callable;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

@Command(mixinStandardHelpOptions = true,
    resourceBundle = App.DEFAULT_I18N_BASENAME,
    versionProvider = MavenVersionProvider.class)
public class MainCommand implements Callable<Integer> {

  private final EmailService emailService;
  private final I18nService i18nService;
  private final Converter<EmailData, Properties> propertiesConverter;

  @ArgGroup(exclusive = false)
  private CommandData commandData;

  public MainCommand() {
    emailService = DefaultEmailService.getInstance();
    i18nService = DefaultI18nService.getInstance();
    propertiesConverter = EmailPropertiesConverter.getInstance();
  }

  @Override
  public Integer call() {
    ResponseData response = getEmailService().send(completeEmailData(getCommandData()));

    if (getCommandData().shouldExport()) {
      printJavaProperties();
    }

    return response.isSuccess() ? 0 : 1;
  }

  private void printJavaProperties() {
    System.out.println("\r\n" + getI18nService().getString("export.title"));
    System.out.println("------------------------------------");
    System.out.println(getPropertiesConverter().convert(getCommandData()));
  }

  private EmailData completeEmailData(EmailData emailData) {
    if (!StringUtils.isAllEmpty(emailData.getUsername(), emailData.getPassword())) {
      emailData.setUseAuthentication(true);
    }

    if (!StringUtils.isAllEmpty(emailData.getSubject(), emailData.getBody())
        || emailData.getAttachment() != null) {
      emailData.setDetailedMessage(true);
    }

    return emailData;
  }

  protected CommandData getCommandData() {
    return commandData;
  }

  protected EmailService getEmailService() {
    return emailService;
  }

  protected I18nService getI18nService() {
    return i18nService;
  }

  protected Converter<EmailData, Properties> getPropertiesConverter() {
    return propertiesConverter;
  }
}
