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

    private static String url = "jdbc:mysql://www.guanweiming.com:3306/accident?characterEncoding=UTF-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useUnicode=true&serverTimezone=Hongkong";
    private static String username = "accident";
    private static String password = "accident@2020";
    private static String outPutDir = "target" + File.separator + "java";

    private static String[] tablePrefix = new String[]{"t_", "j_"};
    private static String[] fieldPrefix = new String[]{"f"};
    private static String packageName = "com.coding";
    private static String[] tables = {

            "airdetail",
            "manager",
            "reason",
            "type",
    };

    public static void main(String[] args) {

        GlobalConfig config = new GlobalConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(url)
                .setUsername(username)
                .setPassword(password)
                .setDriverName("com.mysql.jdbc.Driver");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)
                .setEntityLombokModel(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setTablePrefix(tablePrefix)
                .setFieldPrefix(fieldPrefix)
                .entityTableFieldAnnotationEnable(true)
                .setLogicDeleteFieldName("fdel_flag")
                .setInclude(tables);
        config.setActiveRecord(false)
                .setAuthor("关卫明")
                .setOutputDir(outPutDir)
                .setFileOverride(true);
        new AutoGenerator().setGlobalConfig(config).setStrategy(strategyConfig)
                .setDataSource(dataSourceConfig).setPackageInfo(
                new PackageConfig()
                        .setParent(packageName)
                        .setEntity("domain"))
                .setTemplateEngine(new FreemarkerTemplateEngine()).execute();
    }
}