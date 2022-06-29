package effitiveJava.chapter2;

import effitiveJava.chapter2.model.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <h2>Chapter 2</h2>
 * <h>Creating and Destroying Object</h>
 */
@Slf4j
@SuppressWarnings("all")
public class Item1 {

    public static Boolean valueOf(boolean b) {
        return b ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * builder
     */
    @Test
    public void getUser() {
        User dab = User.builder().name("bob")
                .firstName("alice")
                .lastName("lastname")
                .phone("13213")
                .address("Beijing")
                .build();

        log.info("user: {}", dab);
    }

}
