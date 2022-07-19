package thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author violet
 * @since 2022/7/19
 */
public abstract class GenericExcretory<T> {

    ThreadPoolExecutor poolExecutor;

    GenericExcretory() {
        this.poolExecutor =  new ThreadPoolExecutor(2,4,60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(200),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    public void offer(){
        poolExecutor.execute(new Worker());
    }

    abstract protected void consumer();

    public class Worker implements Runnable{

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                tryConsumer();
            }
        }

        protected void tryConsumer() {
            consumer();
        }
    }

}
