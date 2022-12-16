package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * @author gjd
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
  @Override
  public <T extends Number> Converter<String, T> getConvert(Class<T> targetType) {
    return new StringToNumber<T>(targetType);
  }

  private static final class StringToNumber<T extends Number> implements Converter<String, T> {
    private final Class<?> targetType;

    public StringToNumber(Class<?> targetType) {
      this.targetType = targetType;
    }

    @Override
    public T convert(String source) {
      if (source.length() == 0) {
        return null;
      }
      if (targetType.equals(Integer.class)) {
        return (T) Integer.valueOf(source);
      } else if (targetType.equals(Long.class)) {
        return (T) Long.valueOf(source);
      } else {
        //TODO 暂不支持
        throw new IllegalArgumentException(
            "Cannot convert String [" + source + "] to target class [" + targetType.getName() + "]");
      }
    }
  }
}
