package com.hzqing.generator.service;

import com.hzqing.generator.domain.TableInfo;

import java.util.List;

public interface IGeneratorService {

    List<TableInfo> selectTableList(TableInfo tableInfo);
}
