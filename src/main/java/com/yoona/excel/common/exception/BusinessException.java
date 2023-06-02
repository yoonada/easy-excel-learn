package com.yoona.excel.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description: 自定义业务异常
 * @date 2023-06-02 15:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends RuntimeException {

    private String errorInfo;

    public BusinessException(String message) {
        super(message);
        this.errorInfo = message;
    }

}
