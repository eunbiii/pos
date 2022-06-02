package com.kakaopaycoding.eunbi.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * Created by eunbi on 2022/05/31
 */
@Mapper
public interface OrderMapper {
    void insertOrder(OrderVo vo);
    void insertOrderDtl(OrderDtlVo vo);

    void updateOrderStat(OrderVo vo);
    List<OrderVo> selectOrder(OrderDto orderId);
}
