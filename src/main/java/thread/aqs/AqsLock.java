package thread.aqs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link java.util.concurrent.locks.AbstractQueuedSynchronizer}
 *
 * @author violet
 * @since 2022/9/17
 */
@Slf4j
public class AqsLock {

    @Test
    public void count_down_condition() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);

        Thread[] t = new Thread[10];
        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(() -> {
                count.getAndIncrement();
                log.info("已拼团 {} 人", count);
                countDownLatch.countDown();
            }, "线程" + i);
        }

        Thread last = new Thread(() -> {
            try {
                countDownLatch.await();
                log.info("拼团成功，共{}人", count);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "汇总线程");

        for (Thread thread : t) {
            thread.start();
        }

        last.start();

        last.join();
    }

    @Test
    public void semaphore_condition() throws InterruptedException {
        // NOTE: 只允许5个线程同时执行. like lock
        Semaphore semaphore = new Semaphore(5);
        Thread[] threads = new Thread[15];
        for (int i = 0; i < threads.length; i++) {
            final int finalI = i;
            threads[i] = new Thread(() -> {
                try {
                    semaphore.acquire();
                    log.info("线程{}占用信号量", finalI);
                    TimeUnit.SECONDS.sleep(3);
                    log.info("线程{}释放信号量",finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }, "线程" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }

    @Test
    public void cyclic_barrier() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            log.info("触发循环");
        });

        Thread[] t = new Thread[10];
        for (int i = 0; i < t.length; i++) {
            t[i] = new Thread(() -> {
                try {
                    log.info("线程启动!");
                    cyclicBarrier.await();
                    TimeUnit.SECONDS.sleep(3);
                    log.info("-------");
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (Thread thread : t) {
            thread.start();
        }

        for (Thread thread : t) {
            thread.join();
        }
    }

}
