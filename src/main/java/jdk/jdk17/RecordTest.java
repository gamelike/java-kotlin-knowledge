package jdk.jdk17;

import org.junit.Test;

public class RecordTest {

    @Test
    public void recordTests(){
        RecordEntity record = new RecordEntity("jdk/jdk17",17);
        // print message "RecordEntity(name=jdk17, age=17)" //
        System.out.println(record);
    }

}
