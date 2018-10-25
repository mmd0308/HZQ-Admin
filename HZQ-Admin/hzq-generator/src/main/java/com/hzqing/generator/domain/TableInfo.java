package com.hzqing.generator.domain;

import com.hzqing.common.base.domain.BaseEntity;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
public class TableInfo extends BaseEntity {
    /**
     * 表名称
     */
    private String tableName;
    /**
     * 表描述
     */
    private String tableComment;

}
