package com.kakaopaycoding.eunbi.order;

import com.kakaopaycoding.eunbi.global.GlobalVo;
import com.kakaopaycoding.eunbi.order.OrderDtlVo;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * Created by eunbi on 2022/05/31
 */
@Getter
@Setter
@ToString
@SuperBuilder
@RequiredArgsConstructor
public class OrderVo  extends GlobalVo {
    private String orderId;      /*주문아이디*/
    private String userId;    /*유저*/
    private int orderPrice;      /*주문총가격*/
    private String orderStat;      /*주문상태 : 01=접수, 02:결제완료, 03: 제작중 */
    private String orderDate;       /*주문일자(YYYYMMDD)*/

    public String getKafkaMsg(){
        return "{" +
                "orderId : " + orderId+
                ", userId : " + userId +
                ", orderPrice : " + orderPrice+
                "}";
    }
 }
