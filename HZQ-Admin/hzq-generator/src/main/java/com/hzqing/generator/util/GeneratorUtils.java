package com.hzqing.generator.util;

import com.hzqing.generator.domain.ColumnInfo;
import com.hzqing.generator.domain.TableInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hzqing.common.util.StringUtils.convertToCamelCase;

/**
 * 代码生成器工具类
 * @author hzqing
 */
public class GeneratorUtils {
    /** 类型转换 */
    public static Map<String, String> javaTypeMap = new HashMap<String, String>();
    /**
     * 包基础路径
     */
    public static String BASE_PATH = "java/com/hzqing/";
    /**
     * MyBatis xml文件基础路径
     */
    public static String BASE_MAPPER_XML_PATH = "resources/mapper/";

    /**
     * 将表名转成实体类名称
     * @param tableName
     * @return
     */
    public static String tableNameToClassName(String tableName) {
        // 取出表明前缀
        tableName = tableName.substring(tableName.indexOf("_")+1);
        return convertToCamelCase(tableName);
    }

    /**
     * 将表名，表类型，装成Java类型
     * @param columnInfos
     * @return
     */
    public static List<ColumnInfo> transColumns(List<ColumnInfo> columnInfos) {
        for (ColumnInfo col : columnInfos) {
            col.setAttrName(convertToCamelCase(col.getColumnName()));
            col.setAttrType(javaTypeMap.get(col.getDataType()));
        }
        return columnInfos;
    }

    static
    {
        javaTypeMap.put("tinyint", "Integer");
        javaTypeMap.put("smallint", "Integer");
        javaTypeMap.put("mediumint", "Integer");
        javaTypeMap.put("int", "Integer");
        javaTypeMap.put("integer", "integer");
        javaTypeMap.put("bigint", "Long");
        javaTypeMap.put("float", "Float");
        javaTypeMap.put("double", "Double");
        javaTypeMap.put("decimal", "BigDecimal");
        javaTypeMap.put("bit", "Boolean");
        javaTypeMap.put("char", "String");
        javaTypeMap.put("varchar", "String");
        javaTypeMap.put("tinytext", "String");
        javaTypeMap.put("text", "String");
        javaTypeMap.put("mediumtext", "String");
        javaTypeMap.put("longtext", "String");
        javaTypeMap.put("time", "Date");
        javaTypeMap.put("date", "Date");
        javaTypeMap.put("datetime", "Date");
        javaTypeMap.put("timestamp", "Date");
    }

    /**
     * 根据包路径获取模块的名称
     * @param pacageName
     * @return
     */
    public static String getMoudleName(String pacageName) {
        return pacageName.substring(pacageName.lastIndexOf(".")+1);
    }

    /**
     * 获取所有的模版列表
     * @return
     */
    public static List<String> getTemplates(){
        List<String> templates = new ArrayList<String>(10);
        templates.add("java/controller/BaseController.java.ftl");
        templates.add("java/domain/BaseEntity.java.ftl");
        templates.add("java/mapper/BaseMapper.java.ftl");
        templates.add("java/mapper/BaseMapper.xml.ftl");
        templates.add("java/service/BaseServiceImpl.java.ftl");
        templates.add("java/service/IBaseService.java.ftl");
        return templates;
    }

    /**
     * 根据模版 类名称等信息获取文件的名称
     * @param temp
     * @param tableInfo
     * @return
     */
    public static String getFileName(String temp, TableInfo tableInfo) {
        String javaPath = BASE_PATH + tableInfo.getMoudleName();
        // 生成实体
        if (temp.contains("BaseEntity.java.ftl")) {
            return javaPath + "/domain/" + tableInfo.getClassName() + ".java";
        }
        // 生成controller
        if (temp.contains("BaseController.java.ftl")) {
            return javaPath + "/controller/" + tableInfo.getClassName() + "Controller.java";
        }
        // 生成mapper接口类
        if (temp.contains("BaseMapper.java.ftl")) {
            return javaPath + "/mapper/" + tableInfo.getClassName() + "Mapper.java";
        }
        // 生成Service接口类
        if (temp.contains("IBaseService.java.ftl")) {
            return javaPath + "/service/I" + tableInfo.getClassName() + "Service.java";
        }
        // 生成Service实现类
        if (temp.contains("BaseServiceImpl.java.ftl")) {
            return javaPath + "/service/impl/" + tableInfo.getClassName() + "ServiceImpl.java";
        }
        // 生成controller
        if (temp.contains("BaseMapper.xml.ftl")) {
            return BASE_MAPPER_XML_PATH + tableInfo.getMoudleName() + tableInfo.getClassName() + "Mapper.xml";
        }

        return null;
    }
}
