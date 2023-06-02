package com.yoona.excel.logic.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.yoona.excel.common.dto.ExportExcelDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YoonaDa
 * @email lintiaoda@suntang.com
 * @description:
 * @date 2022-03-09 18:04
 */
@Slf4j
@Component
public class ExcelUtil {

    /**
     * 校验文件格式是否是Excel
     *
     * @return
     */
    public static boolean verifyFileFormat(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        log.info("originalFilename:{}", originalFilename);
        if (StrUtil.isNotBlank(originalFilename)) {
            assert originalFilename != null;
            originalFilename = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            return "xls".equals(originalFilename) || "xlsx".equals(originalFilename);
        }
        return true;
    }

    /**
     * 封装一下基于EasyExcel的导出方法
     *
     * @param response
     * @param excelDTO
     * @throws IOException
     */
    public static void export(HttpServletResponse response, ExportExcelDTO excelDTO) throws IOException {
        String fileName = URLEncoder.encode(excelDTO.getFileName(), "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", String.join("", "attachment;filename=", fileName, ".xlsx"));
        EasyExcel.write(response.getOutputStream(), excelDTO.getBaseClass())
                .sheet(excelDTO.getSheetName())
                .doWrite(excelDTO.getDataList());
    }

    /**
     * 导出路径上的Excel模板
     *
     * @param response
     * @param filePath
     * @param exportFileName
     * @throws IOException
     */
    public static void exportExcelTemplate(HttpServletResponse response, String filePath, String exportFileName) throws IOException {
        log.info("ExcelUtil.exportExcelTemplate.filePath：{}", filePath);
        // 构建文件路径
        File file = new File(filePath);
        if (!file.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("文件不存在。");
            return;
        }
        String fileName = URLEncoder.encode(exportFileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", String.join("", "attachment;filename=", fileName, ".xlsx"));
        // 将文件写入响应输出流
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 有表头没数据的模板
     *
     * @param response
     * @param headerList
     * @param exportFileName
     * @throws IOException
     */
    public static void exportExcelByHeaderList(HttpServletResponse response, List<String> headerList, String exportFileName) throws IOException {
        List<List<String>> headers = new ArrayList<>();
        headerList.forEach(res -> {
            List<String> column = new ArrayList<>();
            column.add(res);
            headers.add(column);
        });
        String fileName = URLEncoder.encode(exportFileName, "UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", String.join("", "attachment;filename=", fileName, ".xlsx"));
        EasyExcel.write(response.getOutputStream())
                .head(headers)
                .sheet("sheet1")
                .doWrite(new ArrayList<>());
    }


}
