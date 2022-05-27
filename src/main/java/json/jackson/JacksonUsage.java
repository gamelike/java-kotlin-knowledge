package json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import json.jackson.dto.JackSonExtend;
import json.jackson.dto.MyBean;
import json.jackson.dto.RawBean;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author violet.
 */
@Slf4j
public class JacksonUsage {

    @SneakyThrows
    public String readFile(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int length;
            StringBuilder sb = new StringBuilder();
            while ((length = fis.read(buffer)) != -1) {
                sb.append(fis.read(buffer, 0, length));
            }
            return sb.toString();
        }
    }

    @SneakyThrows
    @Test
    public void when_jackson_serialize_contains_username() {
        JackSonExtend jackSon = new JackSonExtend();
        jackSon.setName("jackson");
        jackSon.add("username", "violet");
        jackSon.add("password", "********");

        // serialize object to json String.
        String value = new ObjectMapper().writeValueAsString(jackSon);

        log.info("value: {}", value);
        Assert.assertTrue(value.contains("username"));
        Assert.assertTrue(value.contains("jackson"));
    }

    @Test
    @SneakyThrows
    /* serialize object to json String by {@link com.fasterxml.jackson.annotation.JsonPropertyOrder} ordering.. */
    public void jackson_annotation_jsonGetter_use() {
        MyBean myBean = new MyBean("violet", "test getterJson");

        String value = new ObjectMapper().writeValueAsString(myBean);

        log.info("value: {}", value);

        Assert.assertTrue(value.contains("violet"));
        Assert.assertTrue(value.contains("test getterJson"));
    }

    @Test
    @SneakyThrows
    public void jackson_rawJson_use() {
        RawBean rawBean = new RawBean("violet", "{\"name\":\"jackson\"}");

        String value = new ObjectMapper().writeValueAsString(rawBean);

        // no raw json result: value: {"name":"violet","text":"{\"name\":\"jackson\"}"}
        log.info("value: {}", value);

        Assert.assertTrue(value.contains("violet"));
        Assert.assertTrue(value.contains("jackson"));
    }

}
