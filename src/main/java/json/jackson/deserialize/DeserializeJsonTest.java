package json.jackson.deserialize;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.kotlin.KotlinFeature;
import com.fasterxml.jackson.module.kotlin.KotlinModule;
import json.jackson.deserialize.dto.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.format.DateTimeFormatter;

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

    @Test
    @SneakyThrows
    public void when_deserialize_using_custom_deserialize() {
        String json = """
                {
                "id": 1,
                "name": "violet",
                "date": "2021-05-29 13:39:00"
                }
                """;

        BeanWithDeserialize bean = new ObjectMapper()
                .readerFor(BeanWithDeserialize.class)
                .readValue(json);

        Assert.assertEquals("2021-05-29 13:39:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(bean.getDate()));
    }

    @Test
    @SneakyThrows
    public void when_deserialize_using_json_alias() {
        String json = """
                 {
                 "fName": "violet",
                 "lastName": "Evergarden"
                 }
                """;

        BeanWithJsonAlias bean = new ObjectMapper()
                .readerFor(BeanWithJsonAlias.class)
                .readValue(json);

        Assert.assertEquals("violet", bean.firstName());
        Assert.assertEquals("Evergarden", bean.lastName());
    }

    @Test
    @SneakyThrows
    public void deserializer_with_custom() {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new KotlinModule.Builder()
                        .withReflectionCacheSize(512)
                        .configure(KotlinFeature.NullToEmptyCollection, true)
                        .configure(KotlinFeature.NullToEmptyMap, true)
                        .configure(KotlinFeature.NullIsSameAsDefault, false)
                        .configure(KotlinFeature.SingletonSupport, true)
                        .configure(KotlinFeature.StrictNullChecks, true)
                        .build());
        BeanWithCustomDeserializer bean = objectMapper.readValue(
                """
                        {
                            "id": "test",
                            "name": "test_name",
                            "tags": null
                        }
                        """
                , BeanWithCustomDeserializer.class);
        Assert.assertEquals(bean.getId(), "test");
        Assert.assertEquals(bean.getName(), "test_name");
        Assert.assertNotNull(bean.getTags());
    }
}
