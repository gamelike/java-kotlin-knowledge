package application.domain.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gjd
 */
@Slf4j
@RestController(value = "/kafka")
public class KafkaController {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @GetMapping(value = "/topic")
  public void getKafkaMessage(@RequestParam(value = "msg") String msg) {
    kafkaTemplate.send("first", msg);
  }

  @KafkaListener(topics = "first")
  public void consumerTopic(String msg) {
    log.info("消费到消息：{}", msg);
  }

}
