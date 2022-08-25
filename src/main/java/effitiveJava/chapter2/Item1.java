package effitiveJava.chapter2;

import com.google.common.collect.Maps;
import effitiveJava.chapter2.model.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

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

    @Test
    public void map() {
        Map<String,String> map = Maps.newHashMap();
        map.put("key1","hello");
        Assert.assertEquals(map.keySet(),map.keySet());
    }

}
