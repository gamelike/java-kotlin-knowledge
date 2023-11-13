package web;

import application.infrastructure.repository.HistoryRepository;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.model.BulkOperationDTO;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author violet.
 */
@SpringBootTest(classes = BootstrapApplication.class)
@RunWith(SpringRunner.class)
@WebAppConfiguration
@Slf4j
public class BootstrapApplicationSmokingTest {

    @Resource
    private HistoryRepository repository;

    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void smokingTest() {
        Assert.assertNotNull(repository);
    }


    @Test
    @SneakyThrows
    public void run() {
        String res = """
                {
                  "whiteRequest": [
                    {
                      "id": "string",
                      "sourceIp": "string",
                      "alarmName": "string",
                      "distinctIp": "string"
                    }
                  ],
                  "confidenceBulkUpdateRequest": [
                    {
                      "id": "string",
                      "deviceType": "string",
                      "ruleName": "string",
                      "sourceIp": "string",
                      "confidence": 0
                    }
                  ],
                  "judgeCompleteRequest": {
                    "ids": [
                      "string"
                    ],
                    "result": 0,
                    "description": "string",
                    "influence": [
                      "string"
                    ]
                  }
                }
                                """;
        BulkOperationDTO dto = objectMapper.readValue(res.getBytes(StandardCharsets.UTF_8), BulkOperationDTO.class);
        log.info("{}", dto);
    }

    @Test
    @SneakyThrows
    public void jackson_no_creator() {
        String json = """
                {
                "id":"123",
                "result":0,
                "influence":["abc"]
                }
                """;
        Object o = objectMapper.readerFor(RequestDto.class).readValue(json);
        log.info("{}", o);
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    @ToString
    static class RequestDto {
        final String id;
        final int result;
        final String description;
        final List<String> influence;

        @JsonCreator
        RequestDto(@JsonProperty("id") String id, @JsonProperty("result") int result, @JsonProperty("description") String description,
                   @JsonProperty("influence") List<String> influence) {
            this.id = id;
            this.result = result;
            this.description = description;
            this.influence = influence;
        }
    }

    @Test
    @SneakyThrows
    public void jackson_creator_test() {
        String json = """
                {
                "id":"123",
                "result":0,
                "influence":["abc"]
                }
                """;
        ResponseDto o = objectMapper.readValue(json.getBytes(StandardCharsets.UTF_8), ResponseDto.class);
        log.info("{}", o);
    }

    @Getter
    @Setter
    @ToString
    static class ResponseDto {
        private String id;
        private int result;
        private String description;
        private List<String> influence;
    }

}
