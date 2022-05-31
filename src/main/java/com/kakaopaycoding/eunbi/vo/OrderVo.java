package com.kakaopaycoding.eunbi.vo;

import com.kakaopaycoding.eunbi.global.GlobalVo;
import lombok.Builder;
import lombok.Getter;
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
public class OrderVo  extends GlobalVo {
    private String orderId;      /*주문아이디*/
    private String userId;    /*유저*/
    private int orderPrice;      /*주문총가격*/
    private String oderStat;      /*주문상태 : 01=접수, 02:결제완료, 03: 제작중 */
    private String orderDate;       /*주문일자(YYYYMMDD)*/

    private List<OrderDtlVo> orderDtlList;

 }
