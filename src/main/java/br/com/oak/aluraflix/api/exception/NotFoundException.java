package br.com.oak.aluraflix.api.exception;

import br.com.oak.aluraflix.api.model.ErrorCode;

public class NotFoundException extends AbstractException {

  public NotFoundException(ErrorCode errorCode, String friendlyMessage) {
    super(errorCode, friendlyMessage);
  }
}
