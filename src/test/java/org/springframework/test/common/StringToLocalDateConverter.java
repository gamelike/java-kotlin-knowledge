package org.springframework.test.common;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author gjd3
 */
public class StringToLocalDateConverter implements Converter<String, LocalDate> {
  private final DateTimeFormatter DATE_TIME_FORMATTER;

  public StringToLocalDateConverter(String pattern) {
    this.DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(pattern);
  }

  @Override
  public LocalDate convert(String source) {
    return LocalDate.parse(source, DATE_TIME_FORMATTER);
  }
}
