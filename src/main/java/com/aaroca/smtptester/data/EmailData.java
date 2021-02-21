package com.aaroca.smtptester.data;

import java.io.File;

public class EmailData {

  private String host;
  private String port;
  private String to;
  private String from;
  private boolean detailedMessage;
  private String subject;
  private String body;
  private File attachment;
  private boolean useAuthentication;
  private String username;
  private String password;
  private boolean useTLS;
  private boolean useSSL;
  private Integer timeout;
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
