package jdk.jdk10;

import java.util.ArrayList;

/**
 * enhance the java language to extend type interface to declarations of local variables
 * with initializers.
 *
 * @author violet.
 */
public class Variables {

    /**
     * This feature would allow,for example, declarations such as:
     */
    public void varDeclarations() {
        var list = new ArrayList<String>(); // infer ArrayList<String>
        var stream = list.stream();; // infer Stream<String>

        for(var i = 0; i < 2; i++) {
            var j = 1;
            System.out.println(i);
            System.out.println(j);
        }

        System.out.println(list.getClass());
        System.out.println(stream.getClass());
    }

    /**
     * garbage collection interface.
     * <p>improve the source code isolation of different garbage collectors by
     * introducing a clean garbage collector interface</p>
     *
     * <h1>Goals</h1>
     * <li>Better modularity for HotSpot internal GC code</li>
     * <li>Make it simpler to add a new GC to HotSpot without perturbing the current code base</li>
     * <li>Make it easier to exclude a GC from a JDK build</li>
     *
     * @author Roman Kennke.
     */

    /**
     * G1 Garbage collector will support parallel full GC.
     *
     * <p>The number of threads can be controlled by -XX:ParallelGCThreads options,
     * but this will also affect the number of threads used for young and Mixed collections.
     * </p>
     */

}
