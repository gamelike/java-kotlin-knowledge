package thread;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

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

        while(true) {
            if (deque.size()>1000) Thread.sleep(10 * 1000);
            deque.offer(index++);
        }
    }

}
