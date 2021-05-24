package com.atguigu.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

/**
 * create by Shipeixin on  2021-05-24  18:23
 */
public class CallBackProducer {

    public static void main(String[] args) {
        //创建配置信息
        Properties properties = new Properties();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.10.103:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");


        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);


        /**
        *  若指定了分区，都会存在指定分区中，若没有分区，则按key的 hashcode值来存
        * */
        for (int i = 0; i < 10; i++){
            producer.send(new ProducerRecord<>("third", 0,"at主线程gudfsigu28","ATGUIGU" + i), (recordMetadata, exception) -> {
                // 如果返回的exception 为null  则说明
                if (exception == null){
                    System.out.println("partition :" + recordMetadata.partition() +  "  " +  "offset:" +recordMetadata.offset());
                }else{
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                }
            });
        }

        producer.close();
    }

}
