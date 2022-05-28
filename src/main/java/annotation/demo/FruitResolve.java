package annotation.demo;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author gjd
 */
@Slf4j
public class FruitResolve {
  public static Fruit loadProperty(Class<? extends Object> cls) throws Exception {
    Field[] declaredFields = cls.getDeclaredFields();
    Fruit fruit = (Fruit) cls.getDeclaredConstructor().newInstance();
    for (Field field : declaredFields) {
      log.info("字段名称{}，字段类型{}", field.getName(), field.getType());
      if (field.isAnnotationPresent(FruitName.class)) {  //读取注解
        //读取注解的值
        double price = field.getAnnotation(FruitName.class).price();
        String value = field.getAnnotation(FruitName.class).value();
        log.info("price={}", price);
        log.info("name={}", value);

        if (field.getName().equals("name")) { //名称
          field.set(fruit, value);
        }
        if (field.getType().equals(Double.class)) {
          field.set(fruit, price);
        }
      }
    }
    return fruit;
  }
}
