package com.yoona.excel.logic.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-03-09 18:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExportExcelDTO {

    /**
     * 导出时显示的文件名
     */
    private String fileName;

    /**
     * 默认第一个sheet的名字
     */
    private String sheetName;

    /**
     * 数据转换映射的类
     */
    private Class<?> baseClass;

    /**
     * 列表数据
     */
    private List<?> dataList;

}
