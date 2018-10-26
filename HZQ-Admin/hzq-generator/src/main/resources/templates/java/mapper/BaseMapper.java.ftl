package com.hzqing.${moudleName}.mapper;

import com.hzqing.${moudleName}.domain.${className};

import java.util.List;

public interface ${className}Mapper {
    List<${className}> selectTableList();

    int insert${className}(${className} ${classNameLower});

    /**
    * 根据用户Id获取用户信息
    * @param ${classNameLower}Id
    * @return
    */
    ${className} select${className}ById(String ${classNameLower}Id);

    /**
    * 批量删除用户
    * @param ${classNameLower}Ids 用户Id数组
    * @return
    */
    int delete${className}ByIds(String[] ${classNameLower}Ids);

    /**
    * 修改用户
    * @param ${classNameLower}
    * @return
    */
    int update${className}(${className} ${classNameLower});
}
