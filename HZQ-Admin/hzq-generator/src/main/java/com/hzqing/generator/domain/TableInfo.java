package com.hzqing.generator.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TableInfo {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;
    /**
     * 表创建时间
     */
    private Date createTime;
    /**
     * 表更新时间
     */
    private Date updateTime;
    /**
     * 表名转成类名 采用驼峰法 第一个字母大写
     */
    private String className;

    /**
     * 实体类名称全部小写
     */
    private String classNameLower;
    /**
     * 表中的所有列
     */
    private List<ColumnInfo> columns;
    /**
     * 根据包路径生成模块名称
     */
    private String moudleName;

    /**
     * 代码生成作者，生成代码时候用
     */
    private String author;

}
