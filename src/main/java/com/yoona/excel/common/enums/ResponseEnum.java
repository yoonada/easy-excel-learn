package com.yoona.excel.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description: 响应枚举
 * @date 2022-05-09 14:40
 */
@Getter
@AllArgsConstructor
public enum ResponseEnum implements IBaseEnum<Integer> {

    /**
     * 枚举所有响应
     */
    SUCCESS(1000, "成功"),

    FAIL(2000, "系统异常"),

    TOKEN_INVALID(401,"令牌失效，请重新登录")

    ;
    private final Integer value;

    private final String description;


}
