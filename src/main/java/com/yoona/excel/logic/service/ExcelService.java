package com.yoona.excel.logic.service;

import com.yoona.excel.common.resp.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2023-06-02 10:42
 */
public interface ExcelService {

    /**
     * 动态解析表头、处理数据（兼容导入不同的模板，可动态去读取分析）
     *
     * @param file
     * @return
     */
    BaseResponse dynamicReadParseAfterImport(MultipartFile file);

}
