package com.hzqing.${moudleName}.mapper;

import com.hzqing.${moudleName}.domain.${className};

import java.util.List;
/**
 *
 * @description ${tableComment}
 * @author ${author}
 */
public interface ${className}Mapper {
    /**
     * 根据条件检索列表
     * @return
     */
    List<${className}> selectTableList();
    /**
     * 新增 ${className}
     * @param ${classNameLower}
     * @return
     */
    int insert${className}(${className} ${classNameLower});

    /**
    * 根据${classNameLower}Id获取用户信息
    * @param ${classNameLower}Id
    * @return 返回${className}
    */
    ${className} select${className}ById(String ${classNameLower}Id);

    /**
    * 根据${classNameLower}Id批量删除${className}
    * @param ${classNameLower}Ids 用户Id数组
    * @return 返回影响行
    */
    int delete${className}ByIds(String[] ${classNameLower}Ids);

    /**
    * 修改${className}
    * @param ${classNameLower}
    * @return 返回影响行数
    */
    int update${className}(${className} ${classNameLower});
}
