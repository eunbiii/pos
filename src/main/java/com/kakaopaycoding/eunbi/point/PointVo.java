package com.kakaopaycoding.eunbi.point;

import com.kakaopaycoding.eunbi.global.GlobalVo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * Created by eunbi on 2022/05/31
 */
@Getter
@Setter
@ToString
@SuperBuilder
public class PointVo extends GlobalVo {
    private String pointSeq;      /*포인트아이디*/
    private String userId;      /*사용자아이디*/
    private String orderId;    /*주문아이디*/
    private int pointTot;      /*포인트*/
    private String useYn;      /*사용여부*/
    private String description;      /*비고*/
}
