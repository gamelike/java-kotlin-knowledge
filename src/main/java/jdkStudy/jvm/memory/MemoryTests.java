package jdkStudy.jvm.memory;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.List;

@Slf4j
public class MemoryTests {

    @Test
    public void test_allocate() {
        int count = 1_000_000;
        allocateHeap(count);
        allocateMemory(count);
    }

    private List<ByteBuffer> allocateHeap(int count) {
        List<ByteBuffer> buffers = Lists.newArrayListWithCapacity(count);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            ByteBuffer allocate = ByteBuffer.allocate(2);
            buffers.add(allocate);
        }
        long endTime = System.currentTimeMillis();
        log.info("申请堆内存{}次,耗费时间为{}ms", count, endTime - startTime);
        return buffers;
    }

   private List<ByteBuffer> allocateMemory(int count) {
       List<ByteBuffer> buffers = Lists.newArrayListWithCapacity(count);
       long startTime = System.currentTimeMillis();
       for (int i = 0; i < count; i++) {
           ByteBuffer allocate = ByteBuffer.allocateDirect(2);
           buffers.add(allocate);
       }
       long endTime = System.currentTimeMillis();
       log.info("申请RAM内存{}次,耗费时间为{}ms", count, endTime - startTime);
       return buffers;
   }
}