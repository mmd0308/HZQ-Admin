package com.hzqing.${moudleName}.service.impl;

import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.${className};
import com.hzqing.system.mapper.${className}Mapper;
import com.hzqing.system.service.I${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 *
 * @description ${tableComment}
 * @author ${author}
 */
@Service
public class ${className}ServiceImpl implements I${className}Service {
    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;
    /**
     * 根据条件检索列表
     * @param ${classNameLower}
     * @return
     */
    public List<${className}> selectTableList(${className} ${classNameLower}) {
        return ${classNameLower}Mapper.selectTableList();
    }

    /**
     * 新增${className}
     * @param ${classNameLower}
     * @return
     */
    public int insert${className}(${className} ${classNameLower}) {
        ${classNameLower}.set${className}Id(UUIDUtils.get32UUID());
        return ${classNameLower}Mapper.insert${className}(${classNameLower});
    }

    /**
    * 根据${classNameLower}Id获取信息
    * @param ${classNameLower}Id
    * @return
    */
    public ${className} select${className}ById(String ${classNameLower}Id) {
        return ${classNameLower}Mapper.select${className}ById(${classNameLower}Id);
    }
    /**
     * 根据${classNameLower}Id批量删除${className}
     * @param ids ${className} Id数组
     * @return 返回影响行
     */
    public int delete${className}ByIds(String ids) {
        String[] ${classNameLower}Ids = ids.split(",");
        return ${classNameLower}Mapper.delete${className}ByIds(${classNameLower}Ids);
    }
    /**
     * 修改${className}
     * @param ${classNameLower}
     * @return 返回影响行数
     */
    public int update${className}(${className} ${classNameLower}) {
        return ${classNameLower}Mapper.update${className}(${classNameLower});
    }
}
