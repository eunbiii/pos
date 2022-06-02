package com.kakaopaycoding.eunbi.kafka;

import com.kakaopaycoding.eunbi.global.ResponseDto;
import com.kakaopaycoding.eunbi.order.OrderDto;
import com.kakaopaycoding.eunbi.order.OrderService;
import com.kakaopaycoding.eunbi.order.OrderVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eunbi on 2022/06/02
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaService kafkaService;

    @PostMapping(value = "/initKafka")
    @Description("카프카초기화")
    public ResponseDto<Object> initKafka(@RequestBody KafkaDto dto){
        kafkaService.initKafka(dto.getKafkaServer());

        return ResponseDto.builder()
                .code("ok")
                .message("카프카초기화.")
                .build();
    }

    @PostMapping(value = "/closeKafka")
    @Description("카프카종료")
    public ResponseDto<Object> closeKafka(){
        kafkaService.closeKafka();
        return ResponseDto.builder()
                .code("ok")
                .message("카프카종료.")
                .build();
    }

    @PostMapping(value = "/sendMsg")
    @Description("메세지전송")
    public ResponseDto<Object> sendMsg(@RequestBody KafkaDto dto){
        kafkaService.produceMsg(dto.getMsg(), dto.getKafkaTopic());
        return ResponseDto.builder()
                .code("ok")
                .message("카프카 메세지 전송 : "+ dto.getMsg())
                .build();
    }

}
