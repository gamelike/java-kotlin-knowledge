package jdkStudy.jdk14;

import java.lang.reflect.InvocationTargetException;

/**
 * jdk14 features.
 *
 * @author violet.
 */
public class Jdk14 {

    /**
     * instanceof extends.
     */
    public void instanceofExtends(Object o) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (o instanceof String s && s.length() > 0) {
            System.out.println(s.contains("a"));
            System.out.println(s);
        } else if (o instanceof BaseData b) {
            // can't invoke method in compile scope.
            System.out.println(b.getClass());
            System.out.println(b.getClass().getMethod("getId").invoke(b));
        }
    }

}
