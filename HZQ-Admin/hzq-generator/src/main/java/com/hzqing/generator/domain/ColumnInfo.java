package com.hzqing.generator.domain;

import lombok.Data;

/**
 * 数据库列信息
 *
 * @author hzqing
 */
@Data
public class ColumnInfo {
    /** 字段名称 */
    private String columnName;

    /** 字段类型 */
    private String dataType;

    /** 列描述 */
    private String columnComment;

    /** Java属性类型 */
    private String attrType;

    /**
     * Java属性名称(驼峰法)
     */
    private String attrName;

}
