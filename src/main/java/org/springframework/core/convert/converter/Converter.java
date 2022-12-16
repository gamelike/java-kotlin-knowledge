package org.springframework.core.convert.converter;

/**
 * 类型转换抽象接口
 *
 * @author gjd
 */
public interface Converter<S, T> {
  /**
   * 类型转换
   *
   * @param source
   * @return
   */
  T convert(S source);
}
