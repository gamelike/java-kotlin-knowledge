//package jdkStudy.jdk16;
//
//
//import jdk.incubator.foreign.MemoryAddress;
//import jdk.incubator.vector.FloatVector;
//import jdk.incubator.vector.VectorSpecies;
//
///**
// * @author violet.
// */
//public class Jdk16 {
//
//    static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_256;
//
//    /**
//     * vector API is introduced in JDK 16.
//     */
//    protected void scalarComputation(float[] a, float[] b, float[] c) {
//        // previous code.
//        for (int i = 0; i < a.length; i++) {
//            c[i] = (a[i] * a[i] + b[i] * b[i]) * -1.0f;
//        }
//
//        // new code.
//        for (int i = 0; i < a.length; i += SPECIES.length()) {
//            var m = SPECIES.indexInRange(i, a.length);
//            var va = FloatVector.fromArray(SPECIES, a, i, m);
//            var vb = FloatVector.fromArray(SPECIES, b, i, m);
//            var vc = va.mul(va)
//                    .add(vb.mul(vb))
//                    .neg();
//            vc.intoArray(c, i, m);
//            System.out.println(vc);
//        }
//    }

    // Foreign Linker API is introduced in JDK 16.
    // https://openjdk.java.net/jeps/389
//    protected void foreignLinker(MemoryAddress addr1, MemoryAddress addr2) {
        // void qsort(void *base, size_t nmemb, size_t size,
        //            int (*compar)(const void *, const void *));
        /**
         * class Qsort {
         *     static int qsortCompare(MemoryAddress addr1, MemoryAddress addr2) {
         *             return MemoryAccess.getIntAtOffset(MemorySegment.ofNativeRestricted(),
         *                                                addr1.toRawLongValue()) -
         *                    MemoryAccess.getIntAtOffset(MemorySegment.ofNativeRestricted(),
         *                                                addr2.toRawLongValue());
         *     }
         * }
         */
//    }
//}
