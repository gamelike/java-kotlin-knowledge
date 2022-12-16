package org.springframework.test.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.convert.support.StringToNumberConverterFactory;
import org.springframework.test.common.StringToBooleanConverter;
import org.springframework.test.common.StringToIntegerConverter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * @author gjd3
 */
public class TypeConversionFirstPartTest {
  @Test
  public void testStringToIntegerConveter() {
    StringToIntegerConverter converter = new StringToIntegerConverter();
    Integer num = converter.convert("8888");
    System.out.println(num);
  }

  @Test
  public void testStringToNUmberConverterFactory() {
    StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();

    Converter<String, Integer> stringIntegerConverter = converterFactory.getConvert(Integer.class);
    Integer intNum = stringIntegerConverter.convert("8888");
    assertThat(intNum).isEqualTo(8888);

    Converter<String, Long> stringLongConverter = converterFactory.getConvert(Long.class);
    Long longNum = stringLongConverter.convert("8888");
    assertThat(longNum).isEqualTo(8888);
    assertThat(longNum).isEqualTo(8888L);
  }

  @Test
  public void testGenericConverter() {
    StringToBooleanConverter converter = new StringToBooleanConverter();
    Boolean flag = (Boolean) converter.convert("false", String.class, Boolean.class);
    System.out.println(flag);
  }

  @Test
  public void testGenericConversionService() {
    GenericConversionService conversionService = new GenericConversionService();

    conversionService.addConverter(new StringToIntegerConverter());
    Integer intNum = conversionService.convert("8888", Integer.class);
    System.out.println(intNum);

    conversionService.addConverterFactory(new StringToNumberConverterFactory());
    System.out.println(conversionService.canConvert(String.class, Long.class));
    Long longNum = conversionService.convert("8888", Long.class);
    System.out.println(longNum);

    conversionService.addConverter(new StringToBooleanConverter());
    conversionService.canConvert(String.class,Boolean.class);
    Boolean flag = conversionService.convert("true",Boolean.class);
    System.out.println(flag);
  }
}
