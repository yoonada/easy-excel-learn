package com.yoona.excel.logic.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.fastjson.JSON;
import com.yoona.excel.common.base.BaseResponse;
import com.yoona.excel.common.dto.ExportExcelDTO;
import com.yoona.excel.common.response.SystemResponse;
import com.yoona.excel.logic.domain.dto.export.TableOneExportDTO;
import com.yoona.excel.logic.domain.dto.imports.TableOneImportDTO;
import com.yoona.excel.logic.domain.entity.TableOne;
import com.yoona.excel.logic.listener.TableOneImportListener;
import com.yoona.excel.logic.service.ExcelService;
import com.yoona.excel.logic.service.TableOneService;
import com.yoona.excel.logic.util.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
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
    public BaseResponse traditionalOrdinaryImport(MultipartFile file) throws IOException {
        boolean verified = ExcelUtil.verifyFileFormat(file);
        if (!verified) {
            return SystemResponse.fail("不支持该格式的文件");
        }
        TableOneImportListener importListener = new TableOneImportListener();
        EasyExcel.read(file.getInputStream(), TableOneImportDTO.class, importListener)
                // 第几行作为表头读取
                .headRowNumber(1)
                .sheet()
                .doRead();
        List<Map<Integer, String>> headList = importListener.getHeadList();
        if (CollectionUtil.isEmpty(headList)) {
            return SystemResponse.fail("导入的Excel未包含表头");
        }
        log.info("headList：{}", JSON.toJSONString(headList));
        return SystemResponse.success("导入成功");
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
