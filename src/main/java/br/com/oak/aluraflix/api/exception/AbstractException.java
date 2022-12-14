package br.com.oak.aluraflix.api.exception;

import br.com.oak.aluraflix.api.model.ErrorCode;

public class AbstractException extends RuntimeException {

  private ErrorCode errorCode;
  private String friendlyMessage;

  public AbstractException(ErrorCode errorCode, String friendlyMessage) {
    super(errorCode.name());
    this.errorCode = errorCode;
    this.friendlyMessage = friendlyMessage;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }

  public String getFriendlyMessage() {
    return friendlyMessage;
  }
}
