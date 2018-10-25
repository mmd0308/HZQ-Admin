package com.hzqing.generator.service.impl;

import com.hzqing.generator.domain.TableInfo;
import com.hzqing.generator.mapper.GeneratorMapper;
import com.hzqing.generator.service.IGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneratorServiceImpl implements IGeneratorService {
    @Autowired
    private GeneratorMapper generatorMapper;


    public List<TableInfo> selectTableList(TableInfo tableInfo) {
         return generatorMapper.selectTableList(tableInfo);
    }
}
