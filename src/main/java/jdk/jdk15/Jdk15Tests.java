package jdk.jdk15;

import org.junit.Test;

/**
 * @author violet.
 */
public class Jdk15Tests {

    private final Jdk15 jdk15 = new Jdk15();

    @Test
    public void RecordTests() {
        jdk15.recordReflection();
    }
}
