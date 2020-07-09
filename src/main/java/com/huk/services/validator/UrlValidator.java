package com.huk.services.validator;

import com.huk.exception.ValidationException;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Component;

@Component
public class UrlValidator /*implements Validator<String>*/ {

  public void validate(String url) {
    try {
      Jsoup.connect(url);
//      exception should be specific
    } catch (Exception e) {
      throw new ValidationException("Incorrect url", e);
    }
  }

//  public ValidatorType getType() {
//    return ValidatorType.URL;
//  }
}
