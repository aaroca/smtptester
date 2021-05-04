package com.aaroca.smtptester.data;

import com.aaroca.smtptester.utils.Constants.Mail;
import java.io.File;
import picocli.CommandLine.Help.Visibility;
import picocli.CommandLine.Option;

public class EmailData {

  @Option(names = {"-s", "--host"},
      required = true,
      descriptionKey = "main.host.help")
  private String host;

  @Option(names = {"-p", "--port"},
      defaultValue = Mail.DEFAULT_PORT_COMMAND,
      fallbackValue = Mail.DEFAULT_PORT_COMMAND,
      showDefaultValue = Visibility.ALWAYS,
      descriptionKey = "main.port.help")
  private String port;

  @Option(names = {"-t", "--to"},
      defaultValue = Mail.DEFAULT_EMAIL,
      fallbackValue = Mail.DEFAULT_EMAIL,
      showDefaultValue = Visibility.ALWAYS,
      descriptionKey = "main.to.help")
  private String to;

  @Option(names = {"-f", "--from"},
      defaultValue = Mail.DEFAULT_EMAIL,
      fallbackValue = Mail.DEFAULT_EMAIL,
      showDefaultValue = Visibility.ALWAYS,
      descriptionKey = "main.from.help")
  private String from;

  private boolean detailedMessage;

  @Option(names = {"-j", "--subject"},
      defaultValue = Mail.DEFAULT_CONTENT,
      fallbackValue = Mail.DEFAULT_CONTENT,
      showDefaultValue = Visibility.ALWAYS,
      descriptionKey = "main.subject.help")
  private String subject;

  @Option(names = {"-b", "--body"},
      defaultValue = Mail.DEFAULT_CONTENT,
      fallbackValue = Mail.DEFAULT_CONTENT,
      showDefaultValue = Visibility.ALWAYS,
      descriptionKey = "main.body.help")
  private String body;

  @Option(names = {"-a", "--attachment"},
      descriptionKey = "main.attachment.help")
  private File attachment;

  private boolean useAuthentication;

  @Option(names = {"-u", "--username"},
      descriptionKey = "main.username.help")
  private String username;

  @Option(names = {"-w", "--password"},
      descriptionKey = "main.password.help")
  private String password;

  @Option(names = {"--tls"},
      descriptionKey = "main.tls.help")
  private boolean useTLS;

  @Option(names = {"--ssl"},
      descriptionKey = "main.ssl.help")
  private boolean useSSL;

  @Option(names = {"-o", "--timeout"},
      defaultValue = Mail.DEFAULT_TIMEOUT_COMMAND,
      fallbackValue = Mail.DEFAULT_TIMEOUT_COMMAND,
      showDefaultValue = Visibility.ALWAYS,
      descriptionKey = "main.timeout.help")
  private Integer timeout;

  @Option(names = {"-d", "--debug"},
      descriptionKey = "main.debug.help")
  private boolean debug;

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public boolean isDetailedMessage() {
    return detailedMessage;
  }

  public void setDetailedMessage(boolean detailedMessage) {
    this.detailedMessage = detailedMessage;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public File getAttachment() {
    return attachment;
  }

  public void setAttachment(File attachment) {
    this.attachment = attachment;
  }

  public boolean isUsingAuthentication() {
    return useAuthentication;
  }

  public void setUseAuthentication(boolean useAuthentication) {
    this.useAuthentication = useAuthentication;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isUsingTLS() {
    return useTLS;
  }

  public void setUseTLS(boolean useTLS) {
    this.useTLS = useTLS;
  }

  public boolean isUsingSSL() {
    return useSSL;
  }

  public void setUseSSL(boolean useSSL) {
    this.useSSL = useSSL;
  }

  public Integer getTimeout() {
    return timeout;
  }

  public void setTimeout(Integer timeout) {
    this.timeout = timeout;
  }

  public boolean isDebugEnabled() {
    return debug;
  }

  public void setDebug(boolean debug) {
    this.debug = debug;
  }
}
