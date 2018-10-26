package com.hzqing.generator.mapper;

import com.hzqing.generator.domain.ColumnInfo;
import com.hzqing.generator.domain.TableInfo;

import java.util.List;

public interface GeneratorMapper {
    List<TableInfo> selectTableList(TableInfo tableInfo);

    TableInfo selectTableByName(String tableName);

    List<ColumnInfo> selectTableColumnsByName(String tableName);
}
