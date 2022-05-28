package json.jackson.deserialize;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.jackson.deserialize.dto.BeanAnyProperties;
import json.jackson.deserialize.dto.BeanWithCreator;
import json.jackson.deserialize.dto.BeanWithInject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author violet.
 */
@Slf4j
public class DeserializeJsonTest {

    @Test
    @SneakyThrows
    public void deserialize_creator_json() {
        String json = """
                {
                "id": 1,
                "theName": "violet"
                }
                """;

        BeanWithCreator bean = new ObjectMapper()
                .readerFor(BeanWithCreator.class)
                .readValue(json);

        log.info(bean.toString());

        Assert.assertEquals("violet", bean.getName());
    }

    @Test
    @SneakyThrows
    public void when_deserialize_using_jacksonInject() {
        String json = """
                {
                "name": "violet"
                }
                """;

        InjectableValues inject = new InjectableValues.Std()
                .addValue(int.class, 1);

        BeanWithInject bean = new ObjectMapper().reader(inject)
                .forType(BeanWithInject.class)
                .readValue(json);

        log.info("bean: {}", bean.toString());

        Assert.assertEquals("violet", bean.name());
        Assert.assertEquals(1, bean.id());
    }

    @Test
    @SneakyThrows
    public void when_deserialize_using_jackson_any_setter() {
        String jackson = """
                {
                "name": "violet",
                "attr": "attr",
                "id": 1
                }
                """;

        BeanAnyProperties bean = new ObjectMapper()
                .readerFor(BeanAnyProperties.class)
                .readValue(jackson);

        Assert.assertEquals("violet", bean.getName());
        Assert.assertEquals("attr", bean.getProperties().get("attr"));
        Assert.assertEquals(1, bean.getProperties().get("id"));
    }

}
