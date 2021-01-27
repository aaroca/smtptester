package com.aaroca.smtptester.data;

import java.util.Properties;

public class ResponseData {
  private Boolean success;
  private String status;
  private Properties sessionProperties;
  private Throwable exception;

  public ResponseData(Boolean success, String status) {
    this.success = success;
    this.status = status;
  }

  public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Properties getSessionProperties() {
    return sessionProperties;
  }

  public void setSessionProperties(Properties sessionProperties) {
    this.sessionProperties = sessionProperties;
  }

  public Throwable getException() {
    return exception;
  }

  public void setException(Throwable exception) {
    this.exception = exception;
  }
}
