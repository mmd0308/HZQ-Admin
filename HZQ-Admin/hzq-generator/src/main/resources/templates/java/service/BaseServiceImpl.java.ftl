package com.hzqing.${moudleName}.service.impl;

import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.${className};
import com.hzqing.system.mapper.${className}Mapper;
import com.hzqing.system.service.I${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ${className}ServiceImpl implements I${className}Service {
    @Autowired
    private ${className}Mapper ${classNameLower}Mapper;

    public List<${className}> selectTableList() {
        return ${classNameLower}Mapper.selectTableList();
    }

    /**
    * 新增用户
    * @param ${classNameLower}
    * @return
    */
    public int insert${className}(${className} ${classNameLower}) {
        ${classNameLower}.set${className}Id(UUIDUtils.get32UUID());
        return ${classNameLower}Mapper.insert${className}(${classNameLower});
    }

    /**
    * 根据用户Id获取用户信息
    * @param ${classNameLower}Id
    * @return
    */
    public ${className} select${className}ById(String ${classNameLower}Id) {
        return ${classNameLower}Mapper.select${className}ById(${classNameLower}Id);
    }

    public int delete${className}ByIds(String ids) throws Exception {
        String[] ${classNameLower}Ids = ids.split(",");
        for(String ${classNameLower}Id : ${classNameLower}Ids) {
            if (${className}.isAdmin(${classNameLower}Id)) {
                throw new Exception("不允许修改管理员用户");
            }
        }
        return ${classNameLower}Mapper.delete${className}ByIds(${classNameLower}Ids);
    }

    public int update${className}(${className} ${classNameLower}) {
        return ${classNameLower}Mapper.update${className}(${classNameLower});
    }
}
