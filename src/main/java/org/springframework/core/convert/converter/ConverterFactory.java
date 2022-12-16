package org.springframework.core.convert.converter;

/**
 * 类型转化接口工厂
 *
 * @author gjd
 */
public interface ConverterFactory<S, R> {
  <T extends R> Converter<S, T> getConvert(Class<T> targetType);
}
