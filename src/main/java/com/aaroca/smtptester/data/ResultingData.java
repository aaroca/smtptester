package com.aaroca.smtptester.data;

public class ResultingData {
  private Boolean success;
  private String status;
  private Throwable exception;

  public ResultingData(Boolean success, String status) {
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

  public Throwable getException() {
    return exception;
  }

  public void setException(Throwable exception) {
    this.exception = exception;
  }
}
