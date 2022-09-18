package thread;

import org.junit.Test;

import java.util.List;

/**
 * @author violet
 * @since 2022/9/3
 */
public class TestWriteAndRead {

    @Test
    public void test() throws InterruptedException {
        List<Integer> list = new TestList<>();
        new Thread(()->{
            list.add(1);
        },"write").start();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(list.get(0));
        },"read");
        thread.start();
        thread.join();
    }

}
