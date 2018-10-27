package com.hzqing.generator.service.impl;

import com.hzqing.common.constant.Constants;
import com.hzqing.generator.domain.ColumnInfo;
import com.hzqing.generator.domain.GeneratorRule;
import com.hzqing.generator.domain.TableInfo;
import com.hzqing.generator.mapper.GeneratorMapper;
import com.hzqing.generator.service.IGeneratorService;
import com.hzqing.generator.util.GeneratorUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class GeneratorServiceImpl implements IGeneratorService {

    private final static Logger logger = LoggerFactory.getLogger(GeneratorServiceImpl.class);

    @Autowired
    private GeneratorMapper generatorMapper;
    @Autowired
    private Configuration freeMarkConfig;


    public List<TableInfo> selectTableList(TableInfo tableInfo) {
         return generatorMapper.selectTableList(tableInfo);
    }

    /**
     * 根据表名称生成代码
     * @param generatorRule
     * @return
     */
    public byte[] generatorCode(GeneratorRule generatorRule) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : generatorRule.getTables()) {
            // 查询表信息
            TableInfo table = generatorMapper.selectTableByName(tableName);
            // 查询列信息
            List<ColumnInfo> columns = generatorMapper.selectTableColumnsByName(tableName);
            // 生成代码
            generatorCode(table, columns, zip,generatorRule);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    /**
     * 按照模版生成代码
     * @param tableInfo
     * @param columnInfos
     * @param zipOutputStream
     */
    public void generatorCode(TableInfo tableInfo, List<ColumnInfo> columnInfos, ZipOutputStream zipOutputStream,GeneratorRule generatorRule){
        // 生成实体类
        // 表名转成实体类名称
        String entityName = GeneratorUtils.tableNameToClassName(tableInfo.getTableName());
        tableInfo.setClassName(entityName);
        tableInfo.setClassNameLower(entityName.toLowerCase());
        // 获取模块名
        tableInfo.setMoudleName(generatorRule.getMoudleName());
        // 将表的列名，类型，装成Java实体类的属性和Java类型
        tableInfo.setColumns(GeneratorUtils.transColumns(columnInfos));

        tableInfo.setAuthor(generatorRule.getAuthor());

        // 获取所有的模版 如：controller，domian service
        List<String> templates = GeneratorUtils.getTemplates();
        for (String temp : templates) {
            try {
                Template template = freeMarkConfig.getTemplate(temp);
                String string = FreeMarkerTemplateUtils.processTemplateIntoString(template, tableInfo);
                // 添加到zip
                zipOutputStream.putNextEntry(new ZipEntry(GeneratorUtils.getFileName(temp,tableInfo)));
                IOUtils.write(string, zipOutputStream, Constants.UTF8);
                zipOutputStream.closeEntry();
            } catch (Exception e) {
               logger.error("模块渲染失败，表名：" + tableInfo.getTableName(),e);
            }
        }
    }
}
