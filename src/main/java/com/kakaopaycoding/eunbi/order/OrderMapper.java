package com.kakaopaycoding.eunbi.order;

import org.apache.ibatis.annotations.Mapper;


/**
 * Created by eunbi on 2022/05/31
 */
@Mapper
public interface OrderMapper {
    void insertOrder(OrderVo vo);
    void insertOrderDtl(OrderDtlVo vo);

    void updateOrder(OrderVo vo);
    void deleteOrder(OrderVo vo);
}
