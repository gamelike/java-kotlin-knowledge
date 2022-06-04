package application.infrastructure.annotation;

import java.lang.annotation.*;

/**
 * 启用jpa的filter
 *
 * @author violet.
 */
@Target({java.lang.annotation.ElementType.TYPE, java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EnableFilter {

    /**
     * 查询字段
     */
    String value();

    /**
     * 查询filter的名称
     */
    String name() default "";

    /**
     * 过滤器名称
     */
    String filterName() default "filter";

}
