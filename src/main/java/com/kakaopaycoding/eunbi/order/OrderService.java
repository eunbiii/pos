package com.kakaopaycoding.eunbi.order;

import com.kakaopaycoding.eunbi.global.ValidationUtil;
import com.kakaopaycoding.eunbi.kafka.KafkaService;
import com.kakaopaycoding.eunbi.point.PointService;
import com.kakaopaycoding.eunbi.point.PointVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by eunbi on 2022/05/31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper mapper;
    private final PointService pointService;
    private final KafkaService kafkaProducer;
    private final ValidationUtil validationUtil;

    /**
     * 주문
     */
    @Transactional(readOnly = true)
    public OrderVo doOrder(OrderDto dto)  throws Exception {
        // 주문 확인
        validationUtil.validateUserPoint(dto.getUserId(), dto.getOrderPrice());
        validationUtil.validateOrder(dto);

        // 2. 주문 INSERT : 상태 = 접수
        OrderVo orderVo = OrderVo.builder()
                .userId(dto.getUserId())
                .orderPrice(dto.getOrderPrice())
                .systemId(dto.getSystemId())
                .build();
        mapper.insertOrder(orderVo);
        String orderId = orderVo.getOrderId();

        //주문상세 insert
        List<OrderDtlVo> orderDtlList = dto.getOrderDtlList();
        orderDtlList.forEach(dtlVo -> {
            int tot = dtlVo.getMenuPrice() * dtlVo.getOrderCnt();
            dtlVo.setMenuPriceTot(tot);
            dtlVo.setOrderId(orderId);
            mapper.insertOrderDtl(dtlVo);
        });

        // 3. Point 차감, 차감내용 반영
        PointVo pointVo = PointVo.builder()
                .orderId(orderId)
                .userId(orderVo.getUserId())
                .systemId(orderVo.getSystemId())
                .pointTot(orderVo.getOrderPrice())
                .build();
        pointService.pointUse(pointVo);

        // 4. 주문상태 : 결제완료
        orderVo.setOderStat("02");
        //mapper.updateOrder(vo);

        // 주문정보 Kafka 전송
        kafkaProducer.produceMsg(orderVo.getKafkaMsg());
        return orderVo;
    }



}
