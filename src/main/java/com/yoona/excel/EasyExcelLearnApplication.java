package com.yoona.excel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dell
 */
@MapperScan("com.yoona.excel.logic.mapper")
@SpringBootApplication
public class EasyExcelLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyExcelLearnApplication.class, args);
    }

}
