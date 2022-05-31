package com.kakaopaycoding.eunbi.order;

import com.kakaopaycoding.eunbi.vo.OrderDtlVo;
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
public class OrderDto {
    private String orderId;      /*주문아이디*/
    private String userId;    /*유저*/
    private int orderPrice;      /*주문총가격*/
    private String oderStat;      /*주문상태 : 01=접수, 02:결제완료, 03: 제작중 */
    private String orderDate;       /*주문일자(YYYYMMDD)*/
    private String systemId;

    private List<OrderDtlVo> orderDtlList;

}
