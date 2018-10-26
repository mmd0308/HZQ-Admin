package com.hzqing.${moudleName}.service;

import com.hzqing.${moudleName}.domain.${className};

import java.util.List;

public interface I${className}Service {
    List<${className}> selectTableList();

    int insert${className}(${className} ${classNameLower});

    ${className} select${className}ById(String ${classNameLower}Id);

    int delete${className}ByIds(String ids) throws Exception;

    int update${className}(${className} ${classNameLower});
    }
