package org.springframework.core.convert.converter;


/**
 * 类型转换接口注册接口
 *
 * @author gjd
 */
public interface ConverterRegistry {
  void addConverter(Converter<?, ?> converter);

  void addConverterFactory(ConverterFactory<?, ?> converterFactory);

  void addConverter(GenericConverter genericConverter);
}
