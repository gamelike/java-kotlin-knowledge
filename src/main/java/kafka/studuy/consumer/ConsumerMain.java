package kafka.studuy.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author gjd
 */
@Slf4j
public class ConsumerMain {
    public static void main(String[] args) {
        //配置
        Properties properties = new Properties();
        //集群参数
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.56.104:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); //key的反序列化
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); //value的反序列化
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test4"); //组ID
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true); //自动提交offset
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000); //按照时间提交 定时
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        //消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        //选择Topic
        List<String> topics = new ArrayList<>();
        topics.add("test");
        consumer.subscribe(topics);

        //订阅 topcis对应的分区
//        List<TopicPartition> topics = new ArrayList<>();
//        topics.add(new TopicPartition("first", 1));
//        consumer.assign(topics);
//
//        //指定消费者的offset
//        Set<TopicPartition> assignment = consumer.assignment();
//        for (TopicPartition topicPartition : assignment) {
//            consumer.seek(topicPartition, 0);
//        }


        //消费
        while (true) {
            //拉取数据
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
            //序列化打印消息
            for (ConsumerRecord<String, String> record : records) {
                log.info("key:{},value:{}", record.key(), record.value());
            }

        }

        //关闭
    }
}
