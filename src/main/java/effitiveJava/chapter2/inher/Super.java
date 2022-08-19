package effitiveJava.chapter2.inher;

/**
 * @author violet
 */
public class Super {
    // Broken - constructor invokes an overrideMe method.
    public Super() {
        overrideMe();
    }

    public void overrideMe() {

    }
}
