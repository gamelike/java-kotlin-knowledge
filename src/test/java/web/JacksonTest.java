package web;

import json.jackson.common.dto.BeanWithIgnore;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@JsonTest
@RunWith(SpringRunner.class)
public class JacksonTest {

    @Autowired
    JacksonTester<BeanWithIgnore> jacksonTester;

    @Test
    public void test_jackson_tester() throws IOException {
        JsonContent<BeanWithIgnore> write = jacksonTester.write(new BeanWithIgnore(1, "test", "description"));
        String json = write.getJson();
        Assertions.assertThat(json).isEqualTo("""
                {
                    "name":"test"
                }
                """);
    }

}
