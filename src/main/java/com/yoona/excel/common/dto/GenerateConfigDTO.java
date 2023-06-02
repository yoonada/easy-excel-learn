package com.yoona.excel.common.dto;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: yoonada
 * @email: m15602498163@163.com
 * @create: 2021-09-07 19:18
 * @description: 代码生成器配置类
 */
@Data
public class GenerateConfigDTO implements Serializable {

    private static final long serialVersionUID = -7050777695289314154L;

    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 代码生成的类的父包名称
     */
    private String parentPackage;
    /**
     * 去掉表的前缀
     */
    private String[] tablePrefix;

    /**
     * 代码生成包含的表，可为空，为空默认生成所有
     */
    private String[] includeTables;

    /**
     * 生成代码里，注释的作者
     */
    private String author;

    /**
     * 数据库类型
     */
    private DbType dbType;

    /**
     * jdbc驱动
     */
    private String jdbcDriver;

    /**
     * 数据库连接地址
     */
    private String jdbcUrl;

    /**
     * 数据库账号
     */
    private String jdbcUserName;

    /**
     * 数据库密码
     */
    private String jdbcPassword;

    /**
     * 代码生成目录
     */
    private String outputDir;
}
