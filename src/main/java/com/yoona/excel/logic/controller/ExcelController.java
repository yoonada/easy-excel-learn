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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @ApiOperationSupport(order = 1)
    @ApiOperation("传统普通的导出（导出具体某个表成Excel）（这里导出test_table_one）")
    @GetMapping("/traditionalOrdinaryExport")
    public void traditionalOrdinaryExport(HttpServletResponse response) throws IOException {
        excelService.traditionalOrdinaryExport(response);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation("传统普通的导入（导入Excel，将数据解析存储到具体某个表中）（这里导入Excel的数据存储到test_table_one）")
    @PostMapping("/traditionalOrdinaryImport")
    public BaseResponse traditionalOrdinaryImport(MultipartFile file) throws IOException {
        return excelService.traditionalOrdinaryImport(file);
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation("动态解析表头、处理数据（兼容导入不同的模板，可动态去读取分析）")
    @PostMapping("/dynamicReadParseAfterImport")
    public BaseResponse dynamicReadParseAfterImport(MultipartFile file) {
        return excelService.dynamicReadParseAfterImport(file);
    }


}
