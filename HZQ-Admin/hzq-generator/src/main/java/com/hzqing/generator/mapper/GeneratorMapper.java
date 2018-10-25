package com.hzqing.generator.mapper;

import com.hzqing.generator.domain.TableInfo;

import java.util.List;

public interface GeneratorMapper {
    List<TableInfo> selectTableList(TableInfo tableInfo);
}
