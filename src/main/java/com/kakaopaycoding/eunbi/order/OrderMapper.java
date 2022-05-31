package com.kakaopaycoding.eunbi.order;

import com.kakaopaycoding.eunbi.vo.MenuVo;
import com.kakaopaycoding.eunbi.vo.OrderDtlVo;
import com.kakaopaycoding.eunbi.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


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
