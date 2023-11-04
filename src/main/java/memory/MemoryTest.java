package memory;

import sun.misc.Unsafe;

public class MemoryTest {
    public static void main(String[] args) {
        long memory = Unsafe.getUnsafe().allocateMemory(1024 * 8 * 10 * 10);

        Unsafe.getUnsafe().freeMemory(memory);
    }
}
