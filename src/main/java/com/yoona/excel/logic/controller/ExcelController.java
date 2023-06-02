package com.yoona.excel.logic.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.yoona.excel.common.base.BaseResponse;
import com.yoona.excel.logic.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2023-06-02 10:20
 */
@Api(tags = "Excel")
@CrossOrigin
@RestController
@ApiSupport(order = 1)
@RequiredArgsConstructor
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelService excelService;

    @ApiOperationSupport(order = 5)
    @ApiOperation("动态解析表头、处理数据（兼容导入不同的模板，可动态去读取分析）")
    @PostMapping("/dynamicReadParseAfterImport")
    public BaseResponse dynamicReadParseAfterImport(MultipartFile file) {
        return excelService.dynamicReadParseAfterImport(file);
    }


}
