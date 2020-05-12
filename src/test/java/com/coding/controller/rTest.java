package com.coding.controller;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.io.File;

public class rTest {


    @Test
    public void computeSize() {

    }

    private static String url = "jdbc:mysql://cdb-boq0qdam.bj.tencentcdb.com:10127/airtext?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true&serverTimezone=Hongkong";
    private static String username = "root";
    private static String password = "12345678Aa";
    private static String outPutDir = "target" + File.separator + "java";

    private static String[] tablePrefix = new String[]{"t_", "j_"};
    private static String[] fieldPrefix = new String[]{"f"};
    private static String packageName = "com.coding";
    private static String[] tables = {

            "airdetail",
            "manager",
            "reason",
            "type",
            "user",
    };

    public static void main(String[] args) {

        GlobalConfig config = new GlobalConfig();//全局配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();// 数据源配置
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password)
                .setDriverName("com.mysql.cj.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();// 策略配置
        strategyConfig.setCapitalMode(true)//驼峰命名
                .setEntityLombokModel(true)//【实体】是否为lombok模型（默认 false）
                .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略,该处下划线转驼峰命名
                .setTablePrefix(tablePrefix)// 此处可以修改为您的表前缀
                .setFieldPrefix(fieldPrefix)
                .entityTableFieldAnnotationEnable(true)
                .setLogicDeleteFieldName("fdel_flag")
                .setInclude(tables);//需要包含的表名，允许正则表达式（与exclude二选一配置）
        config.setActiveRecord(false)//开启 ActiveRecord 模式 默认false
                .setAuthor("张冬晓")//开发人员
                .setOutputDir(outPutDir)//生成文件的输出目录
                .setFileOverride(true);//是否覆盖已有文件 默认false
        // 代码生成器
        new AutoGenerator().setGlobalConfig(config).setStrategy(strategyConfig)
                .setDataSource(dataSourceConfig).setPackageInfo(
                new PackageConfig()
                        .setParent(packageName)
                        .setEntity("domain"))////实体类包名
                .setTemplateEngine(new FreemarkerTemplateEngine()).execute();
    }
}