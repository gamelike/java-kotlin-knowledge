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
    String[] value();

}
