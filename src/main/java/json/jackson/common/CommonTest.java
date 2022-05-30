package json.jackson.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.jackson.common.dto.BeanWithIgnore;
import json.jackson.common.dto.BeanWithIgnoreIncluded;
import json.jackson.common.dto.BeanWithIgnoreType;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author violet.
 */
@Slf4j
public class CommonTest {

    @Test
    @SneakyThrows
    public void serialize_with_json_ignore() {
        String json = """
                {
                "id": 1,
                "name": "violet",
                "description": "this is a test"
                }
                """;

        BeanWithIgnore bean = new ObjectMapper()
                .readerFor(BeanWithIgnore.class)
                .readValue(json);

        // ignore id
        Assert.assertNull(bean.id());
        Assert.assertEquals("violet", bean.name());
        Assert.assertNull(bean.description());
    }

    @Test
    @SneakyThrows
    public void serialize_with_json_ignore_type() {
        BeanWithIgnoreType.IgnoreType ignoreType = new BeanWithIgnoreType.IgnoreType("Violet", "Evergarden");

        BeanWithIgnoreType bean = new BeanWithIgnoreType(1, ignoreType);

        String json = new ObjectMapper()
                .writeValueAsString(bean);

        log.info("json: {}", json);
        Assert.assertFalse(json.contains("Violet"));
        Assert.assertFalse(json.contains("Evergarden"));
    }

    @Test
    @SneakyThrows
    public void when_serialize_ignore_include_not_null() {
        BeanWithIgnoreIncluded bean = new BeanWithIgnoreIncluded(null, "Evergarden");

        String json = new ObjectMapper().writeValueAsString(bean);

        Assert.assertTrue(json.contains("lastName"));
        Assert.assertFalse(json.contains("firstName"));
    }
}
