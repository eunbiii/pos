package com.kakaopaycoding.eunbi.kafka;

import com.kakaopaycoding.eunbi.order.OrderDtlVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by eunbi on 2022/06/01
 */
@Getter
@Setter
@ToString
public class KafkaDto {
    private String userId;    /*유저*/
    private String msg;     /*메세지*/
    private String kafkaServer;     /*카프카서버*/
    private String kafkaTopic;      /*토픽*/
}