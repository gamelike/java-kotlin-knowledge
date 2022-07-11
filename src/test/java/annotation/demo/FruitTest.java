package annotation.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author gjd
 */
@Slf4j
public class FruitTest {

    @Test
    public void runApple() {
        try {
            Fruit fruit = FruitResolve.loadProperty(Fruit.class);
            log.info("最终结果{}", fruit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
