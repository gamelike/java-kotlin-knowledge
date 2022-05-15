package jdk.jdk12;

import java.util.Scanner;

/**
 * jdk12 features.
 *
 * @author violet.
 */
public class Jdk12 {

    /**
     * <h1>Schenandoah GC</h1>
     * <p> TODO understanding
     * </p>
     *
     * <h1>constant load</h1>
     * <p>{@link java.lang.invoke.ConstantGroup}</p>
     *
     * <h1>Switch Expression</h1>
     * <p>extend switch preview</p>
     */

    protected void switchExpression(Day day) {
        switch (day) {
            case FRIDAY, SATURDAY, SUNDAY -> System.out.println("weekend");
            case MONDAY, TUESDAY, THURSDAY -> System.out.println("work");
            case WEDNESDAY -> System.out.println("game");
            default -> System.out.println("unknown");
        }

        int i = switch (day) {
            case MONDAY -> 1;
            case TUESDAY -> 2;
            case WEDNESDAY -> 3;
            case THURSDAY -> 4;
            default -> 0;
        };
        System.out.println(i);
    }

    protected void switchExpression2(Day day) {
        switch (day) {
            case FRIDAY, SATURDAY, SUNDAY -> System.out.println("weekend");
            case MONDAY, TUESDAY, THURSDAY -> System.out.println("work");
            case WEDNESDAY -> System.out.println("game");
            default -> System.out.println("unknown");
        }
    }
}
