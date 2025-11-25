package jdkStudy.jvm.heap;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;

@Slf4j
public class ObjectUtils {

    @org.junit.Test
    public void test_object_utils_memory_byte() {
        Test test = new Test();
        log.info("object message : {}", ClassLayout.parseInstance(test).toPrintable());
        test.name = "test";
        test.age = 24;
        test.sex = true;
        test.content = "test";
        TestA testA = new TestA();
        testA.name = "test";
        test.testA = testA;
        log.info("object message : {}", ClassLayout.parseInstance(test).toPrintable());
        System.out.println("Total Size: " + GraphLayout.parseInstance(test).totalSize() + " bytes");
    }

    public static class Test {
        public String name;
        public boolean sex;
        public int age;
        public String content;
        public TestA testA;
    }

    public static class TestA {
        public String name;
    }

}
