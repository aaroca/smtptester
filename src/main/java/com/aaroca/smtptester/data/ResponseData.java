package com.aaroca.smtptester.data;

import javax.mail.Session;

public class ResponseData {

  private Boolean success;
  private String status;
  private Session session;
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

  public Session getSession() {
    return session;
  }

  public void setSession(Session session) {
    this.session = session;
  }

  public Throwable getException() {
    return exception;
  }

  public void setException(Throwable exception) {
    this.exception = exception;
  }
}
