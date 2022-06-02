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
     * @param dto
     * @return 주문정보
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public OrderVo doOrder(OrderDto dto)  throws Exception {
        // 1. 주문 확인
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

        // 3. 주문상세 insert
        List<OrderDtlVo> orderDtlList = dto.getOrderDtlList();
        orderDtlList.forEach(dtlVo -> {
            int tot = dtlVo.getMenuPrice() * dtlVo.getOrderCnt();
            dtlVo.setMenuPriceTot(tot);
            dtlVo.setOrderId(orderId);
            mapper.insertOrderDtl(dtlVo);
        });

        // 4. Point 차감, 차감내용 반영
        PointVo pointVo = PointVo.builder()
                .orderId(orderId)
                .userId(orderVo.getUserId())
                .systemId(orderVo.getSystemId())
                .pointTot(orderVo.getOrderPrice())
                .build();
        pointService.pointUse(pointVo);

        // 5. 주문상태 : 결제완료
        orderVo.setOrderStat("02");
        mapper.updateOrderStat(orderVo);

        // 6. 주문정보 Kafka 전송
        kafkaProducer.produceMsg(orderVo.getKafkaMsg());
        return orderVo;
    }

    /**
     * 주문취소
     * @param dto
     * @return 주문정보
     * @throws Exception
     */
    @Transactional(readOnly = true)
    public OrderVo refundOrder(OrderDto dto)  throws Exception {
        // 1. 환불정보 체크
        List<OrderVo> orderList = mapper.selectOrder(dto);
        validationUtil.validateRefund(dto, orderList);

        // 2. 상태 업데이트
        OrderVo orderVo = orderList.get(0);
        orderVo.setOrderStat("03"); // 03:환불
        mapper.updateOrderStat(orderVo);

        // 3. 포인트 재적립
        PointVo pointVo = PointVo.builder()
                .orderId(dto.getOrderId())
                .userId(orderVo.getUserId())
                .systemId(orderVo.getSystemId())
                .description("주문 취소 (주문번호 : "+dto.getOrderId()+")")
                .pointTot(orderVo.getOrderPrice())
                .build();
        pointService.pointAdd(pointVo);

        return orderVo;
    }

}
