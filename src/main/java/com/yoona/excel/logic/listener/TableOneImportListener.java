package com.yoona.excel.logic.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yoona.excel.logic.domain.dto.imports.TableOneImportDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2023-06-02 13:58
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class TableOneImportListener extends AnalysisEventListener<TableOneImportDTO> {

    /**
     * 表头数据（存储读取到的表头数据）
     */
    private final List<Map<Integer, String>> headList = new ArrayList<>();

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

    @Override
    public void invoke(TableOneImportDTO tableOneImportDTO, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    public List<Map<Integer, String>> getHeadList() {
        return headList;
    }

}
