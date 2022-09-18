package thread;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author violet
 * @since 2022/9/3
 */
public class TestList<T> extends ArrayList<T> {

    final Lock lock = new ReentrantLock();

    @Override
    public boolean add(T t) {
        lock.lock();
        return super.add(t);
    }

    @Override
    public T get(int index) {
        lock.lock();
        return super.get(index);
    }
}
