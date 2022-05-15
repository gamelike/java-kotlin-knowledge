package jdk.jdk11;

import java.util.concurrent.Flow;

/**
 * jdk 11 features.
 *
 * @author violet.
 */
public class Jdk11 {
    /**
     * <h1>JAVA EE is removed</h1>
     * delete other lib. Java EE and CORBA modules. apache support jarkarta EE.
     *
     * <h1>http client</h1>
     * <p>{@link Flow}</p>
     * <p>it's easy to change HTTP version from 1.1 to 2.0 or 2.0 to 1.1.
     * modules and package is named for {@link java.net.http}</p>
     */

    protected FunctionInterface varInfer() {
        return (var a, var b) -> {
            b += b;
            return a.processor(b);
        };
    }

//    protected FunctionInterface varInferError1() {
//        return (var a, String b) -> {
//            b += b;
//            return a.processor(b);
//        };
//    }
      // infer must all var or all decided type;
//    protected FunctionInterface varInferError2() {
//        return (var a, b) -> {
//            b += b;
//            return a.processor(b);
//        };
//    }
}
