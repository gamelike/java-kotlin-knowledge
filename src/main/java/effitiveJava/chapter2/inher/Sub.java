package effitiveJava.chapter2.inher;

import java.time.Instant;

/**
 * @author violet
 */
public final class Sub extends Super {
    // Blank final, set by constructor;
    private final Instant instant;

    public Sub() {
        this.instant = Instant.now();
    }

    // override method invoked by superclass constructor.
    @Override
    public void overrideMe() {
        System.out.println(instant);
        System.out.println(instant.getEpochSecond()); // NPE
    }

    public static void main(String[] args) {
        Sub sub = new Sub();
        sub.overrideMe(); // first print null.
    }
}
