package thread;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author violet
 * @since 2022/7/19
 */
@Slf4j
public class ThreadTest {

    @SneakyThrows
    @Test
    public void thread_fill_test() {
        BlockingQueue<Integer> deque = new LinkedBlockingDeque<>();
        GenericExcretory<Integer> genericExcretory = new GenericExcretory<>() {
            @Override
            protected void consumer() {
                try {
                    //noinspection UnstableApiUsage
                    Queues.drain(deque, Lists.newArrayListWithCapacity(1000), 10, 10, TimeUnit.SECONDS);
                    System.out.println(deque.size());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("线程中断");
                }
            }
        };

        genericExcretory.offer();

        int index = 0;

        //noinspection InfiniteLoopStatement
        while (true) {
            if (deque.size() > 1000) {//noinspection BusyWait
                Thread.sleep(10 * 1000);
            }
            deque.offer(index++);
        }
    }

    /**
     * 此方法会导致操作系统VM error
     */
    @Test
    public void out_of_ram_test() {
        int index = 0;
        //noinspection InfiniteLoopStatement
        while (true) {
            // NOTE : java-thread使用RAM 物理内存，线程溢出会导致操作系统内存溢出崩溃.
            new Thread(() -> {
                try {
                    log.info("thread name:{}", Thread.currentThread().getName());
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException e) {
                    Thread.interrupted();
                }
            }, "test" + index).start();
            index++;
        }
    }

    @Test
    public void out_of_memory_test() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                int index = 0;
                @SuppressWarnings("MismatchedQueryAndUpdateOfCollection") Map<String, Object> map = Maps.newHashMap();
                while (true) {
                    map.put("oom-" + index, new Object());
                    index++;
                }
            } finally {
                lock.unlock();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(1000);
                lock.lock();
                log.info("success!");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();
        thread2.join();
        log.info("complete");

    }

}
