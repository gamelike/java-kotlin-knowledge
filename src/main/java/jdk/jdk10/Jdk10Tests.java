package jdk.jdk10;

import org.junit.Test;

/**
 * @author violet.
 */
public class Jdk10Tests {

    private final Variables variables = new Variables();

    @Test
    public void test() {
        // print( "class java.util.ArrayList" ); //
        // print( "class java.util.stream.ReferencePipeline$head" ); //
        variables.varDeclarations();

        // var would throw exception //
//        var x = () -> {return "";};
//        var y = null;
        // fix by jdk later //
        var z = method();
        // print( java.lang.String ); //
        System.out.println(z.getClass());
//        var a = this::method;
//        var b = {1,2};
    }

    public String method(){ return "";}

}
