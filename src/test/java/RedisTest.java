import mapstruct.Attributes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author gjd
 */
public class RedisTest {
    Jedis jedis;

    @Before public void init() {
        jedis = new Jedis(new HostAndPort("10.7.212.113", 6380));
        jedis.auth("Talent123");
        Assert.assertEquals(jedis.ping(), "PONG");
    }

    @Test public void testRedisCRUD() {
        jedis.set("abc", "c");
        System.out.println(jedis.get("abc"));
        jedis.lpush("list", "element1", "element2");
        List<String> list = jedis.lrange("list", 0, 10);
        list.stream().forEach(System.out::println);

        Attributes attributes = new Attributes();
        attributes.setId(15L);
        attributes.setName("student");
    }

}
