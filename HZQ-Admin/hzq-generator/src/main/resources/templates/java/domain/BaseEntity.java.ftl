package com.hzqing.${moudleName};

import lombok.Data;
import java.util.*;
/**
 *
 * @description ${tableComment}
 * @author ${author}
 */
@Data
public class ${className} {

<#list columns as col>
    /**
     * ${col.columnComment}
     */
    private ${col.attrType} ${col.attrName};

</#list>
}
