package com.yoona.excel.logic.service.impl;

import com.yoona.excel.common.base.BaseResponse;
import com.yoona.excel.logic.service.ExcelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2023-06-02 10:42
 */
@Slf4j
@Service
public class ExcelServiceImpl implements ExcelService {


    /**
     * 动态解析表头、处理数据（兼容导入不同的模板，可动态去读取分析）
     *
     * @param file
     * @return
     */
    @Override
    public BaseResponse dynamicReadParseAfterImport(MultipartFile file) {

        return null;
    }
}
