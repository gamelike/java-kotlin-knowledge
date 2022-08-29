package jdkStudy.jvm.heap;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.List;
import java.util.Random;

@Slf4j
public class OldArea {

    /**
     * -Xmx60m -Xms60m -XX:NewRatio=2 -XX:SurvivorRatio=8 -XX:+PrintGCDetails
     * -XX:PretenureSizeThreshold
     */
    @Test
    public void big_object_put_old_area() {
        byte[] buffer = new byte[1024 * 1024 * 30];
    }

    @Test
    public void test_heap_procedure_object_step_into_old_area() {
        List<Record> list = Lists.newArrayList();
        while(true) {
            int size = new Random().nextInt(1024 * 1024);
            list.add(new Record(size));
        }
    }

    public static class Record{
        public byte[] bytes;
        public Record(int size) {
            this.bytes = new byte[size];
        }
    }

}
