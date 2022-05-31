package com.kakaopaycoding.eunbi.order;

import com.kakaopaycoding.eunbi.point.PointMapper;
import com.kakaopaycoding.eunbi.point.PointService;
import com.kakaopaycoding.eunbi.vo.OrderDtlVo;
import com.kakaopaycoding.eunbi.vo.OrderVo;
import com.kakaopaycoding.eunbi.vo.PointVo;
import com.kakaopaycoding.eunbi.vo.UserVo;
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


    public boolean checkOrder(OrderDto dto) throws Exception {
        // 1. 주문 확인
        if(dto.getOrderPrice()<=0){
            //주문금액이 잘못되었습니다.
            throw new Exception("주문금액이 잘못되었습니다.");
        }
        if(dto.getOrderDtlList() == null || dto.getOrderDtlList().size()==0){
            //주문 상세내용이 잘못되었습니다.
            throw new Exception("주문 상세내용이 잘못되었습니다.");
        }
        List<OrderDtlVo> orderDtlList = dto.getOrderDtlList();
        int totPrice = orderDtlList.stream().mapToInt(dtlVo -> (dtlVo.getMenuPrice() * dtlVo.getOrderCnt())).sum();
        if(totPrice != dto.getOrderPrice()){
            //주문금액이 잘못되었습니다.
            throw new Exception("주문금액이 잘못되었습니다.");
        }


        return true;
    }

    /**
     * 주문
     */
    @Transactional(readOnly = true)
    public OrderVo doOrder(OrderDto dto)  throws Exception {
        // 주문 확인
        pointService.checkUserPoint(dto.getUserId(), dto.getOrderPrice());
        checkOrder(dto);

        // 2. 주문 INSERT : 상태 = 접수
        OrderVo vo = OrderVo.builder()
                .userId(dto.getUserId())
                .orderPrice(dto.getOrderPrice())
                .systemId(dto.getSystemId())
                .build();
        mapper.insertOrder(vo);
        String orderId = vo.getOrderId();

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
                .userId(vo.getUserId())
                .systemId(vo.getSystemId())
                .pointTot(vo.getOrderPrice())
                .build();
        pointService.pointUse(pointVo);

        // 4. 주문상태 : 결제완료
        vo.setOderStat("02");
        //mapper.updateOrder(vo);
        return vo;
    }

}
