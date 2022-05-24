package jdkStudy.jdk15;

import java.util.Arrays;
/**
 * Jdk15 features.
 *
 * @author violet.
 */
public class Jdk15 {

    /**
     * text blocks standard deploy.
     * use \ transfer.
     */
    public void textBlocks() {
        String text = """
                this is a text block.\
                this is second line.\
                """;
        System.out.println(text);
    }

    /**
     * foreign-memory access API.
     */
    public void foreignMemory() {
        // allocate 100 bytes memory.
//        try (MemorySegment segment = MemorySegment.allocateNative(100)) {
//            System.out.println(segment.address());
//        }
//        VarHandle intHandle = MemoryHandles.varHandle(int.class, ByteOrder.nativeOrder());

//        try (MemorySegment segment = MemorySegment.allocateNative(100L)) {
//
//        }
    }

    /**
     * record reflection.
     *
     * {@link <a href="https://openjdk.java.net/jeps/384">...</a>}
     */
    public void recordReflection() {
        // record reflection.
        System.out.println(Arrays.toString(Doc.class.getRecordComponents()));
    }

}

