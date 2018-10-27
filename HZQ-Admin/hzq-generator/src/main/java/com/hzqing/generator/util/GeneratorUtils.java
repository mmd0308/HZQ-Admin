package com.hzqing.generator.util;

import com.hzqing.common.util.StringUtils;
import com.hzqing.generator.constant.GeneratorConstants;
import com.hzqing.generator.domain.ColumnInfo;
import com.hzqing.generator.domain.GeneratorRule;
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
     * vue 前段页面生成路径
     */
    public static String BASE_VUE_PATH = "resources/vue/";
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
            String attr = convertToCamelCase(col.getColumnName());
            col.setAttrName(attr.substring(0,1).toLowerCase()+attr.substring(1));
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
        templates.add("vue/index.vue.ftl");
        templates.add("vue/index.js.ftl");
        templates.add("vue/components/form.vue.ftl");
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
        // 生成Mapper xml文件
        if (temp.contains("BaseMapper.xml.ftl")) {
            return BASE_MAPPER_XML_PATH + tableInfo.getMoudleName() + "/" + tableInfo.getClassName() + "Mapper.xml";
        }
        // 生成vue列表页面
        if (temp.contains("index.vue.ftl")) {
            return BASE_VUE_PATH + tableInfo.getClassName().toLowerCase() + "/index.vue";
        }
        // 生成请求链接api
        if (temp.contains("index.js.ftl")) {
            return BASE_VUE_PATH + tableInfo.getClassName().toLowerCase() + "/index.js";
        }
        // 生成Form表单
        if (temp.contains("form.vue.ftl")) {
            return BASE_VUE_PATH + tableInfo.getClassName().toLowerCase() +  "/components/form.vue";
        }

        return null;
    }

    /**
     * 如果没有设置生成属性，设置默认属性
     * @param generatorRule
     * @return
     */
    public static GeneratorRule setGeneratorDefault(GeneratorRule generatorRule) {
        if (StringUtils.isEmpty(generatorRule.getPackageName()))
            generatorRule.setPackageName(GeneratorConstants.DEFAULT_PACKAGE_NAME);
        if (StringUtils.isEmpty(generatorRule.getMoudleName()))
            generatorRule.setMoudleName(getMoudleName(generatorRule.getPackageName()));
        if (StringUtils.isEmpty(generatorRule.getAuthor()))
            generatorRule.setAuthor(GeneratorConstants.DEFAULT_AUTHOR);
        return generatorRule;
    }
}
