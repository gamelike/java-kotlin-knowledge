package annotation.demo;

import jdk.javadoc.doclet.Reporter;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gjd
 */
@Target({ElementType.FIELD,ElementType.METHOD})  //指定该annotation 应用的代码位置
@Retention(RetentionPolicy.RUNTIME)  //定义注解的生命周期
@Inherited  //如parent上添加此注解，son extend parent,则son也继承此注解，必须当@Target ElementType.TYPE类型时才生效
@Documented
public @interface FruitName {
  String value() default "default fruit";  //此字段可以直接赋值
  double price() default 50.3;  //此字段可以直接赋值
}
