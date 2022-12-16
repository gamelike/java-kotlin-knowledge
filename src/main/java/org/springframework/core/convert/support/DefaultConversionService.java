package org.springframework.core.convert.support;

import org.springframework.core.convert.converter.ConverterRegistry;

/**
 * @author gjd
 */
public class DefaultConversionService extends GenericConversionService {
  public DefaultConversionService() {
    addDefaultConverters(this);
  }

  public static void addDefaultConverters(ConverterRegistry converterRegistry) {
    converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    //TODO 继续添加其他转化器
  }
}
