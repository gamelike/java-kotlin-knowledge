package com;

import annotation.demo.Dto;
import application.infrastructure.repository.HistoryRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.BulkOperationDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import web.BootstrapApplication;

import javax.annotation.Resource;
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
    @SuppressWarnings("unchecked")
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

}
