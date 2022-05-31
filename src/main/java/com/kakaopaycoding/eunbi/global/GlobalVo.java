package com.kakaopaycoding.eunbi.global;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * Created by eunbi on 2022/05/31
 */
@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class GlobalVo {
    private String systemId;

    private String regDtm;      /*등록일자*/
    private String regId;       /*등록자*/
    private String updDtm;      /*수정일자*/
    private String updId;       /*수정자*/

}
