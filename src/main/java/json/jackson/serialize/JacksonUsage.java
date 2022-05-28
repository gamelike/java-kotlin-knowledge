package json.jackson.serialize;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import json.jackson.serialize.dto.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    @Test
    @SneakyThrows
    public void when_serializing_using_json_value(){
        String enumAsString = new ObjectMapper()
                .writeValueAsString(TypeEnumWithValue.TYPE1);

        log.info("enumAsString: {}", enumAsString);
        Assert.assertEquals("\"type1\"", enumAsString);
    }

    @Test
    @SneakyThrows
    public void when_serializing_using_json_root_name() {

        User user = new User(1, "violet");

        ObjectMapper mapper = new ObjectMapper();

        mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);

        String result = mapper.writeValueAsString(user);

        log.info("result: {}", result);

        Assert.assertTrue(result.contains("\"user\":{\"id\":1,\"name\":\"violet\"}"));
    }


    @Test
    @SneakyThrows
    public void when_serializing_using_json_serialize(){
        LocalDateTime localTime = LocalDateTime.now();

        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .format(localTime);

        JsonSerializeDto dto = new JsonSerializeDto("violet", localTime);

        String value = new ObjectMapper().writeValueAsString(dto);

        log.info("value: {}", value);

        Assert.assertTrue(value.contains(date));
    }

}
