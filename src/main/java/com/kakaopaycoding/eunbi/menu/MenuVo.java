package com.kakaopaycoding.eunbi.menu;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by eunbi on 2022/05/31
 */
@Getter
@Setter
@ToString
public class MenuVo {
    private String menuId;      /*메뉴아이디*/
    private String menuName;    /*메뉴명*/
    private int menuPrice;      /*가격*/
    private String menuCtg;    /*메뉴카테고리*/
    private String menuNum;    /*메뉴순서*/

    private String regDtm;      /*등록일자*/
    private String regId;       /*등록자*/
    private String updDtm;      /*수정일자*/
    private String updId;       /*수정자*/

    private int orderCntTot; /*총판매개수*/
    private int orderRank; /*판매순위*/
}
