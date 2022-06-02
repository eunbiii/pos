package com.kakaopaycoding.eunbi.global;

import com.kakaopaycoding.eunbi.order.OrderDtlVo;
import com.kakaopaycoding.eunbi.order.OrderDto;
import com.kakaopaycoding.eunbi.order.OrderMapper;
import com.kakaopaycoding.eunbi.order.OrderVo;
import com.kakaopaycoding.eunbi.point.PointMapper;
import com.kakaopaycoding.eunbi.point.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by eunbi on 2022/06/03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationUtil {
    private final PointMapper pointMapper;
    private final OrderMapper orderMapper;

    /**
     * 고객 정보확인 (포인트)
     * @param userId
     * @param updatePrice
     * @return validate 결과
     * @throws Exception
     */
    public boolean validateUserPoint(String userId, int updatePrice)  throws Exception {
        List<UserVo> userList = pointMapper.selectUserPoint(userId);
        if(userList ==null || userList.size()<1){
            //사용자가 존재하지 않습니다.
            throw new Exception("사용자가 존재하지 않습니다.");
        }
        UserVo userVo = userList.get(0);
        if(userVo.getPointTot() < updatePrice){
            //사용가능한 포인트가 부족합니다.
            throw new Exception("사용가능한 포인트가 부족합니다.");
        }
        return true;
    }

    /**
     * 주문정보 validation
     * @param dto
     * @return validate 결과
     * @throws Exception
     */
    public boolean validateOrder(OrderDto dto) throws Exception {
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
     * 환정보 validation
     * @param dto
     * @return validate 결과
     * @throws Exception
     */
    public boolean validateRefund(OrderDto dto, List<OrderVo> orderList) throws Exception {
        if( !StringUtils.hasLength(dto.getOrderId()) || !StringUtils.hasLength(dto.getUserId())){
            throw new Exception("주문정보가 잘못되었습니다.");
        }
        if(orderList == null || orderList.size()!=1){
            throw new Exception("주문정보가 잘못되었습니다.");
        }
        OrderVo vo = orderList.get(0);
        if(!vo.getUserId().equals(dto.getUserId())){
            throw new Exception("주문정보와 사용자가 일치하지 않습니다.");
        }
        if(!StringUtils.hasLength(vo.getOrderStat()) || vo.getOrderStat().equals("01")){
            throw new Exception("결제되지 않은 주문정보입니다.");
        }
        if(vo.getOrderStat().equals("03")){
            throw new Exception("이미 환불된 주문정보입니다.");
        }
        return true;
    }

    }
