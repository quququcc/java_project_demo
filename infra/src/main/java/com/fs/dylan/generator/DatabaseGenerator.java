package com.fs.dylan.generator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * @author: dylan
 * @title:
 * @projectName:
 * @description: TODO
 * @date: 2023/8/14 4:31 下午
 */
public class DatabaseGenerator {
    /***
     * 用户名
     **/
    private static final String USERNAME = "root";

    /***
     * 密码
     **/
    private static final String PASSWORD = "Zc123456.";

    /***
     * url 地址
     **/
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/project?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT%2B8";

    /***
     * 文件安装路径
     **/
    private static final String PKG_PATH = System.getProperty("user.dir") + "/infra/src/main/java";

    /***
     * xml安装路径
     **/
    private static final String XML_PATH = System.getProperty("user.dir") + "/infra/src/main/resources/mapper";

    /***
     * 逆向工程生成器
     **/
    public static void main(String[] args) {
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                .globalConfig(builder -> {
                    builder.author("dylan")
                            .commentDate("yyy-MM-dd")
                            .disableOpenDir()
                            .outputDir(PKG_PATH);
                })
                .packageConfig(builder -> {
                    builder.parent("com.fs.dylan")
                            .entity("entity")
                            .service("model")
                            .serviceImpl("model.impl")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, XML_PATH));
                })
                .strategyConfig(builder -> {
                    builder.addTablePrefix("project_")
                            .addInclude("project_employee")
                            .addInclude("project_employee_has_permission")
                            .addInclude("project_employee_has_role")
                            .addInclude("project_permission")
                            .addInclude("project_role")
                            .addInclude("project_role_has_permission")
                            .addInclude("address")
                            .addFieldPrefix("is_");
                })
                .strategyConfig(builder -> {
                    builder.serviceBuilder().formatServiceImplFileName("I%sServiceImpl");
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .enableLombok()
                            .addTableFills(new Column("created_at", FieldFill.INSERT))
                            .addTableFills(new Column("updated_at", FieldFill.INSERT_UPDATE))
                            .logicDeleteColumnName("is_deleted")
                            .logicDeletePropertyName("deleted");
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER);
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
