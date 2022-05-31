package com.kakaopaycoding.eunbi.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by eunbi on 2022/06/01
 */
@Getter
@Setter
@ToString
public class UserVo {
    private String userId;
    private String userName;
    private String userStatCd;
    private String userFgCd;
    private int pointTot;
    private String useYn;
}
