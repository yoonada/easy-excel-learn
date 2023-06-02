package com.yoona.excel.logic.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yoona.excel.logic.domain.dto.imports.BaseImportDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description: 抽取并封装成公共导入监听器
 * @date 2023-06-02 14:47
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class BaseImportListener extends AnalysisEventListener<BaseImportDTO> {

    /**
     * 表头数据（存储读取到的表头数据）
     */
    private final List<Map<Integer, String>> headList = new ArrayList<>();

    /**
     * 数据体
     */
    private final List<BaseImportDTO> dataList = new ArrayList<>();

    /**
     * 获取表头数据并存储成一个表头列表（非必须、看场景使用，比如你需要校验表头时）
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        headList.add(headMap);
    }

    /**
     * 每条数据的解析都会来调用
     *
     * @param baseImportDTO
     * @param analysisContext
     */
    @Override
    public void invoke(BaseImportDTO baseImportDTO, AnalysisContext analysisContext) {
        dataList.add(baseImportDTO);
    }

    /**
     * 所有数据解析完成了都会来调用
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
    }

}
