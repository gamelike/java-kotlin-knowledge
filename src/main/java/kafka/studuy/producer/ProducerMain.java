package kafka.studuy.producer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Map;
import java.util.Properties;

/**
 * @author gjd
 */
@Slf4j
public class ProducerMain {
  @SneakyThrows
  public static void main(String[] args) {
    //配置
    Properties properties = new Properties();

    //集群参数
    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.176.200:9092");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); //key的序列化
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); //key的序列化
//    properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomPartition.class.getName()); //设置自定义分区器
    //提供吞吐量参数配置
    properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384 * 2);//设置批次大小，默认16k
    properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024 * 1024 * 32);//设置缓存大小
    properties.put(ProducerConfig.LINGER_MS_CONFIG, 1); //设置等待时间，超时的话，即使批次未承载满，也会发出数据
    properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");//设置压缩数据，有gzip,snappy,lz4,zstd 默认是none
    //配置数据可靠
    properties.put(ProducerConfig.ACKS_CONFIG,"1");//leader 落盘就会反馈
    properties.put(ProducerConfig.RETRIES_CONFIG,Integer.MAX_VALUE);//重试次数
    //设置幂等性
    properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG,false);//关闭幂等性，数据在单分区内会出现重复的问题，默认是true

    //创建对象
    KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
    //发送数据
    for (int i = 0; i < 10; i++) {
      //不带回调函数
//      producer.send(new ProducerRecord<>("first", i + ":hello world"));

      //带回调函数
//      producer.send(new ProducerRecord<>("first", i + ":hello world"), new Callback() {
//        @SneakyThrows
//        @Override
//        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
//          if (e == null) {
//            log.info("主题是：{}，分区是：{}", recordMetadata.topic(), recordMetadata.partition());
//          }
//        }
//      });

      //同步发送 加上get 为同步，需要等待上一个发送完毕才会继续发送
//      producer.send(new ProducerRecord<>("first", i + ":hello world")).get();

      //指定分区
      producer.send(new ProducerRecord<>("first", 1, "b", i + ":hello world"), new Callback() {
        @SneakyThrows
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
          if (e == null) {
            log.info("主题是：{}，分区是：{}", recordMetadata.topic(), recordMetadata.partition());
          }
        }
      });
    }
    //关闭对象
    producer.close();
  }
}

class CustomPartition implements Partitioner {
  @Override
  public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
    //根据参数决定分区 可以根据值（过滤数据）等信息来决定分区 自由确定特殊功能
    return 0;
  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> configs) {

  }
}
