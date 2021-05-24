package com.atguigu.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * create by Shipeixin on  2021-05-24  17:38
 */
public class MyProducer {

    public static void main(String[] args) {

        // 创建kafka 生产者的配置信息
        Properties properties = new Properties();

        /**
        *   配置的相关信息可以在 对应的  **.Config类中获得
         *
         *
         *   三个 配置类： producerConfig    consumerConfig      clientConfig
        * */
        //  broker-list  指定连接的 kafka 集群
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.103:9092");

        // ack 应答级别
        properties.put("acks", "all");

        // 发送失败重试次数
        properties.put("restries", 3);

        // 批次大小    一次发送数据的大小  单位：字节  16K
        properties.put("batch.size",16384);

        // 等待时间  与批次大小配合    16k发送或者1ms发送
        properties.put("linger.ms", 1);

        //RecordAccumulator  缓冲区大小
        properties.put("buffer.memory", 33554432);

        // key，value的序列化类
        properties.put("key.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer",
                "org.apache.kafka.common.serialization.StringSerializer");


        // 创建生产者对象
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 发送数据
        for (int i = 0; i < 10; i++){
            producer.send(new ProducerRecord<>("second", "atguigu" + i));
        }

        // 关闭资源
        /**
        *  close 方法在调用时，会把内存中的数据全部清掉，发到 kafka中去
        * */
        producer.close();
    }

}
