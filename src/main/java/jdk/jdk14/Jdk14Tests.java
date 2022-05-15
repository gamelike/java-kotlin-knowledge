package jdk.jdk14;


import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author violet.
 */
public class Jdk14Tests {
    private final Jdk14 jdk14 = new Jdk14();

    @Test
    public void InstanceOfTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        jdk14.instanceofExtends("a unit test");

        jdk14.instanceofExtends(new BaseData());
    }


    @Test
    public void recordTests(){
        RecordEntity record = new RecordEntity("jdk/jdk17",17);
        // print message "RecordEntity(name=jdk17, age=17)" //
        System.out.println(record);
    }
}
