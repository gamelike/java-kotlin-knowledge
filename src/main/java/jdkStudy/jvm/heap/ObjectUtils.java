package jdkStudy.jvm.heap;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class ObjectUtils {

    @org.junit.Test
    public void test_object_utils_memory_byte() {
        Test test = new Test();
        log.info("object message : {}", ClassLayout.parseInstance(test).toPrintable());
        test.name = "test";
        test.age = 24;
        test.sex = true;
        log.info("object message : {}", ClassLayout.parseInstance(test).toPrintable());
    }

    private static class Test {
        public String name;
        public boolean sex;
        public int age;
    }

}
