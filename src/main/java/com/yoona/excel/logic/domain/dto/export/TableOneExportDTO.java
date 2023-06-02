package com.yoona.excel.logic.domain.dto.export;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2023-06-02 13:40
 */
@Data
public class TableOneExportDTO {

    @ExcelProperty(value = "账号名")
    private String username;

    @ExcelProperty(value = "密码")
    private String password;

    @ExcelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "手机号码")
    private String phone;
    
}
