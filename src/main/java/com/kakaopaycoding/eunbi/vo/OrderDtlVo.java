package com.kakaopaycoding.eunbi.vo;

import com.kakaopaycoding.eunbi.global.GlobalVo;
import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Created by eunbi on 2022/05/31
 */
@Getter
@Setter
@ToString
@SuperBuilder
@RequiredArgsConstructor
public class OrderDtlVo  extends GlobalVo {
    private String orderId;      /*주문아이디*/
    private String menuId;    /*메뉴아이디*/
    private int orderCnt;      /*메뉴개수*/
    private int menuPrice;      /*메뉴가격*/
    private int menuPriceTot;      /*메뉴총가격*/
}
