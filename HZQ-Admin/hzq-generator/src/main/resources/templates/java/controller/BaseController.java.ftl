package com.hzqing.${moudleName}.controller;

import com.github.pagehelper.PageInfo;
import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import com.hzqing.${moudleName}.domain.${className};
import com.hzqing.${moudleName}.service.I${className}Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @description ${tableComment}
 * @author ${author}
 */
@RestController
@RequestMapping("/hzq/${moudleName}/${classNameLower}")
public class ${className}Controller extends BaseController{
    @Autowired
    private I${className}Service ${classNameLower}Service;

    /**
     * 获取${tableComment}的列表信息，带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseMessage<PageInfo<${className}>> list(int pageNum,int pageSize,${className} ${classNameLower}){
        startPage(pageNum,pageSize);
        List<${className}> ${classNameLower}s = ${classNameLower}Service.selectTableList(${classNameLower});
        return successPage(${classNameLower}s);
    }

    /**
    * 新增${tableComment}信息
    * @param ${classNameLower}
    * @return
    */
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody  ${className} ${classNameLower}) {
        int res = ${classNameLower}Service.insert${className}(${classNameLower});
        return new ResponseMessage().success();
    }

    /**
    * 根据${className} Id修改${tableComment}
    * @param ${classNameLower}Id
    * @return
    */
    @GetMapping("/edit/{${classNameLower}Id}")
    public ResponseMessage<${className}> edit(@PathVariable String ${classNameLower}Id){
        ${className} ${classNameLower} = ${classNameLower}Service.select${className}ById(${classNameLower}Id);
        return new ResponseMessage<${className}>().success(${classNameLower});
    }

    /**
    * 修改${tableComment}
    * @param ${classNameLower}
    * @return
    */
    @PutMapping("/edit")
    public ResponseMessage editSave(@RequestBody ${className} ${classNameLower}) {
        int res = ${classNameLower}Service.update${className}(${classNameLower});
        return new ResponseMessage().success(res);
    }


    /**
    * 删除${tableComment}
    * @param ${classNameLower}Ids 多个ID,使用'，'分割
    * @return
    */
    @DeleteMapping("/remove")
    public ResponseMessage remove(String ${classNameLower}Ids) {
        int res = ${classNameLower}Service.delete${className}ByIds(${classNameLower}Ids);
        return new ResponseMessage().success();
    }

}
