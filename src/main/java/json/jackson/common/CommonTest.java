package json.jackson.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.jackson.common.dto.BeanWithIgnore;
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
    public void serialize_with_json_ignore (){
        String json = """
                  {
                  "id": 1,
                  "name": "violet"
                  }
                  """;

        BeanWithIgnore bean = new ObjectMapper()
                .readerFor(BeanWithIgnore.class)
                .readValue(json);

        // ignore id
        Assert.assertNull(bean.id());
        Assert.assertEquals("violet", bean.name());
    }



}
