package com.yoona.excel.logic.domain.dto.imports;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2023-06-02 13:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TableOneImportDTO extends BaseImportDTO{

    @ExcelProperty(value = "账号名")
    private String username;

    @ExcelProperty(value = "密码")
    private String password;

    @ExcelProperty(value = "年龄")
    private Integer age;

    @ExcelProperty(value = "手机号码")
    private String phone;

}
