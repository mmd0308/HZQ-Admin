package com.hzqing;

import lombok.Data;
/**
 *
 *
 *
 */
@Data
public class ${className} {

<#list columns as col>
    /**
     * ${col.columnComment}
     */
    private ${col.attrType} ${col.attrName}

</#list>
}
