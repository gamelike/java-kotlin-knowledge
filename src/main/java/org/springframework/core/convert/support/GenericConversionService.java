package org.springframework.core.convert.support;

import cn.hutool.core.convert.BasicType;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.converter.GenericConverter.ConvertiblePair;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gjd
 */
public class GenericConversionService implements ConversionService, ConverterRegistry {
  private Map<ConvertiblePair, GenericConverter> converterMap = new HashMap<>();

  @Override
  public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
    GenericConverter converter = getConverter(sourceType, targetType);
    return converter != null;
  }

  @Override
  public <T> T convert(Object source, Class<?> targetType) {
    Class<?> sourceType = source.getClass();
    targetType = (Class<T>) BasicType.wrap(targetType);
    GenericConverter converter = getConverter(sourceType, targetType);
    return (T) converter.convert(source, sourceType, targetType);
  }

  @Override
  public void addConverter(Converter<?, ?> converter) {
    ConvertiblePair typeInfo = getRequiredTypeInfo(converter);
    ConverterAdapter converterAdapter = new ConverterAdapter(typeInfo, converter);
    for (ConvertiblePair convertiblePair : converterAdapter.getConvertibleTypes()) {
      converterMap.put(convertiblePair, converterAdapter);
    }

  }

  private ConvertiblePair getRequiredTypeInfo(Object object) {
    Type[] types = object.getClass().getGenericInterfaces();
    ParameterizedType parameterizedType = (ParameterizedType) types[0];
    Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
    Class sourceType = (Class) actualTypeArguments[0];
    Class targetType = (Class) actualTypeArguments[1];
    return new ConvertiblePair(sourceType, targetType);
  }

  @Override
  public void addConverterFactory(ConverterFactory<?, ?> converterFactory) {
    ConvertiblePair typeInfo = getRequiredTypeInfo(converterFactory);
    ConverterFactoryAdapter converterFactoryAdapter = new ConverterFactoryAdapter(typeInfo, converterFactory);
    for (ConvertiblePair convertiblePair : converterFactoryAdapter.getConvertibleTypes()) {
      converterMap.put(convertiblePair, converterFactoryAdapter);
    }
  }

  @Override
  public void addConverter(GenericConverter genericConverter) {
    for (ConvertiblePair convertiblePair : genericConverter.getConvertibleTypes()) {
      converterMap.put(convertiblePair, genericConverter);
    }
  }

  protected GenericConverter getConverter(Class<?> sourceType, Class<?> targetType) {
    List<Class<?>> sourceTypeCandidates = getHierarchy(sourceType);
    List<Class<?>> targetTypeCandidates = getHierarchy(targetType);
    for (Class<?> sourceCandidate : sourceTypeCandidates) {
      for (Class<?> targetCandidate : targetTypeCandidates) {
        ConvertiblePair convertiblePair = new ConvertiblePair(sourceCandidate, targetCandidate);
        GenericConverter converter = converterMap.get(convertiblePair);
        if (converter != null) {
          return converter;
        }

      }
    }
    return null;
  }

  private List<Class<?>> getHierarchy(Class<?> clazz) {
    List<Class<?>> hierarchy = new ArrayList<>();
    while (clazz != null) {
      hierarchy.add(clazz);
      clazz = clazz.getSuperclass();
    }
    return hierarchy;
  }

  private final class ConverterAdapter implements GenericConverter {
    private final ConvertiblePair typeInfo;
    private final Converter<Object, Object> converter;

    public ConverterAdapter(ConvertiblePair typeInfo, Converter<?, ?> converter) {
      this.typeInfo = typeInfo;
      this.converter = (Converter<Object, Object>) converter;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
      return Collections.singleton(typeInfo);
    }

    @Override
    public Object convert(Object source, Class sourceType, Class targetType) {
      return converter.convert(source);
    }
  }

  private final class ConverterFactoryAdapter implements GenericConverter {
    private final ConvertiblePair typeInfo;
    private final ConverterFactory<Object, Object> converterFactory;

    public ConverterFactoryAdapter(ConvertiblePair typeInfo, ConverterFactory<?, ?> converterFactory) {
      this.typeInfo = typeInfo;
      this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
      return Collections.singleton(typeInfo);
    }

    @Override
    public Object convert(Object source, Class sourceType, Class targetType) {
      return converterFactory.getConvert(targetType).convert(source);
    }
  }
}
