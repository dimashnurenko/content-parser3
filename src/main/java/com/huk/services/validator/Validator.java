package com.huk.services.validator;

public interface Validator<T> {

  void validate(T obj);

  ValidatorType getType();
}
