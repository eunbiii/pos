package com.kakaopaycoding.eunbi.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * Created by eunbi on 2021. 8. 9.
 */
@Service
public class KafkaService {
    @Value("${kafka.bootstrap-servers}")
    private String KAFKA_SERVER;
    @Value("${kafka.topic}")
    private String KAFKA_TOPIC;

    private Producer<String, String> producer;

    /**
     * kafka init
     */
    @PostConstruct
    public void initKafka( ) {
        initKafka(KAFKA_SERVER);
    }
    public void initKafka(String kafkaServer){
        if(kafkaServer==null || !StringUtils.hasLength(kafkaServer)){
            kafkaServer = KAFKA_SERVER;
        }

        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServer);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    /**
     * kafka close
     */
    public void closeKafka(){
        try {
            producer.close();
        }catch (Exception e){

        }
    }

    /**
     * kafka로 메세지를 전송한다.
     * @param msg
     * @param topic
     */
    public void produceMsg(String msg, String topic) {
        if(topic==null || !StringUtils.hasLength(topic)){
            topic = KAFKA_TOPIC;
        }
        producer.send(new ProducerRecord<String, String>(topic, msg));
    }
    public void produceMsg(String msg) {
        producer.send(new ProducerRecord<String, String>(KAFKA_TOPIC, msg));
    }

}
