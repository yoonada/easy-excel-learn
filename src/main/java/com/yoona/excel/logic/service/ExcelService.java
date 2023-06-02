package com.yoona.excel.logic.service;

import com.yoona.excel.common.base.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2023-06-02 10:42
 */
public interface ExcelService {


    /**
     * 传统普通的导出（导出具体某个表成Excel）（这里导出test_table_one）
     *
     * @param response
     * @throws IOException
     */
    void traditionalOrdinaryExport(HttpServletResponse response) throws IOException;


    /**
     * 传统普通的导入（导入Excel，将数据解析存储到具体某个表中）（这里导入Excel的数据存储到test_table_one）
     *
     * @param file
     * @return
     */
    BaseResponse traditionalOrdinaryImport(MultipartFile file) throws IOException;

    /**
     * 动态解析表头、处理数据（兼容导入不同的模板，可动态去读取分析）
     *
     * @param file
     * @return
     */
    BaseResponse dynamicReadParseAfterImport(MultipartFile file);

}
