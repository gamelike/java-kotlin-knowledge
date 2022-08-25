package effitiveJava.chapter2;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.EmptyStackException;

@Slf4j
public class CloneTest {

    @Test
    @SneakyThrows
    public void clone_test() {
        MyStacked myStacked = new MyStacked();
        myStacked.push(123);
        MyStacked clone = myStacked.clone();
        log.info("clone: {}", clone);
        Assert.assertNotSame(clone, myStacked);
    }

}

@ToString
@Getter
final class MyStacked implements Cloneable {

    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public MyStacked() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0) throw new EmptyStackException();
        Object result = elements[--size];

        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    @Override
    protected MyStacked clone() throws CloneNotSupportedException {
        return (MyStacked) super.clone();
    }
}
