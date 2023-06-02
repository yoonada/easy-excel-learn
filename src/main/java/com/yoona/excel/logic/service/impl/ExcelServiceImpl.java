package com.yoona.excel.logic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.yoona.excel.common.base.BaseResponse;
import com.yoona.excel.common.exception.BusinessException;
import com.yoona.excel.logic.domain.dto.ExportExcelDTO;
import com.yoona.excel.common.response.SystemResponse;
import com.yoona.excel.logic.domain.dto.export.TableOneExportDTO;
import com.yoona.excel.logic.domain.dto.imports.BaseImportDTO;
import com.yoona.excel.logic.domain.dto.imports.TableOneImportDTO;
import com.yoona.excel.logic.domain.entity.TableOne;
import com.yoona.excel.logic.service.ExcelService;
import com.yoona.excel.logic.service.TableOneService;
import com.yoona.excel.logic.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2023-06-02 10:42
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {

    private final TableOneService tableOneService;

    /**
     * 传统普通的导出（导出具体某个表成Excel）（这里导出test_table_one）
     *
     * @param response
     */
    @Override
    public void traditionalOrdinaryExport(HttpServletResponse response) throws IOException {
        List<TableOne> tableOneList = tableOneService.list();
        List<TableOneExportDTO> exportDTOList = tableOneList.stream()
                .map(e -> BeanUtil.copyProperties(e, TableOneExportDTO.class))
                .collect(Collectors.toList());
        ExportExcelDTO exportExcelDTO = ExportExcelDTO.builder()
                .baseClass(TableOneExportDTO.class)
                .dataList(exportDTOList)
                .fileName("test_table_one")
                .sheetName("Sheet1")
                .build();
        ExcelUtil.export(response, exportExcelDTO);
    }

    /**
     * 传统普通的导入（导入Excel，将数据解析存储到具体某个表中）（这里导入Excel的数据存储到test_table_one）
     *
     * @param file
     * @return
     */
    @Override
    public BaseResponse traditionalOrdinaryImport(MultipartFile file) {
        boolean verified = ExcelUtil.verifyFileFormat(file);
        if (!verified) {
            return SystemResponse.fail("不支持该格式的文件");
        }
        List<BaseImportDTO> dataDTOList;
        try {
            dataDTOList = ExcelUtil.parseVerifyImportDataReturnDataList(file, TableOneImportDTO.class, 1);
        } catch (BusinessException e) {
            log.error("校验异常：{}", e.getMessage());
            return SystemResponse.fail(e.getMessage());
        } catch (Exception e) {
            log.error("导入解析出现异常：{}", e.getMessage(), e);
            return SystemResponse.fail();
        }
        log.info("dataDTOList：{}", JSON.toJSONString(dataDTOList));
        // 入库
        List<TableOne> tableOneList = dataDTOList.stream()
                .map(res -> BeanUtil.copyProperties(res, TableOne.class))
                .collect(Collectors.toList());
        boolean saveBatch = tableOneService.saveBatch(tableOneList);
        if (saveBatch) {
            return SystemResponse.success("导入成功");
        }
        return SystemResponse.success("导入失败");
    }

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
