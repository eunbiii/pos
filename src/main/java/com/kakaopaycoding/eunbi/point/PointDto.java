package com.kakaopaycoding.eunbi.point;

import com.kakaopaycoding.eunbi.vo.OrderDtlVo;
import lombok.Builder;
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
@Builder
public class PointDto {
    private String pointSeq;      /*포인트아이디*/
    private String userId;      /*사용자아이디*/
    private String orderId;    /*주문아이디*/
    private int pointTot;      /*포인트*/
    private String useYn;      /*사용여부*/
    private String description;      /*비고*/
    private String systemId;


}
