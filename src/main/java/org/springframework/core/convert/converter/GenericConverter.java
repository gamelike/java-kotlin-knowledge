package org.springframework.core.convert.converter;

import lombok.Getter;

import java.util.Set;

/**
 * @author gjd
 */
public interface GenericConverter {

  Set<ConvertiblePair> getConvertibleTypes();

  Object convert(Object source, Class sourceType, Class targetType);


  @Getter
  public static final class ConvertiblePair {
    private final Class<?> sourceType;

    private final Class<?> targetType;

    public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
      this.sourceType = sourceType;
      this.targetType = targetType;
    }


    //需要作为map的key 故需要重新equals和hash方法
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null || obj.getClass() != ConvertiblePair.class) {
        return false;
      }
      ConvertiblePair other = (ConvertiblePair) obj;
      return this.sourceType.equals(other.sourceType) && this.targetType.equals(other.targetType);
    }

    @Override
    public int hashCode() {
      return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
    }
  }
}
