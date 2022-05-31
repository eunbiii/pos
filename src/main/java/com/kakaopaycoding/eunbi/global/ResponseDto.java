package com.kakaopaycoding.eunbi.global;

import lombok.Builder;
import lombok.Getter;

/**
 * Created by eunbi on 2022/05/31
 */
@Builder
@Getter
public class ResponseDto<T> {
    private String message;
    private String code;
    private T result;
}
