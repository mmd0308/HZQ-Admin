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
 */
@RestController
@RequestMapping("/hzq/${moudleName}/${classNameLower}")
public class ${className}Controller extends BaseController{
    @Autowired
    private I${className}Service ${classNameLower}Service;

    /**
     * 获取用户的列表信息，带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseMessage<PageInfo<${className}>> list(int pageNum,int pageSize){
        startPage(pageNum,pageSize);
        List<${className}> users = userService.selectTableList();
        return successPage(users);
    }

    /**
    * 新增用户信息
    * @param user
    * @return
    */
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody  ${className} user) {
        int res = userService.insert${className}(user);
        return new ResponseMessage().success();
    }

    /**
    * 根据用户Id修改用户
    * @param userId
    * @return
    */
    @GetMapping("/edit/{userId}")
    public ResponseMessage<${className}> edit(@PathVariable String userId){
        ${className} user = userService.select${className}ById(userId);
        return new ResponseMessage<${className}>().success(user);
    }

    /**
    * 修改用户
    * @param user
    * @return
    */
    @PutMapping("/edit")
    public ResponseMessage editSave(@RequestBody ${className} user) {
        if (StringUtils.isNotEmpty(user.get${className}Id()) && ${className}.isAdmin(user.get${className}Id())) {
            return new ResponseMessage().success("不允许修改系统管理员");
        }
        int res = userService.update${className}(user);
        return new ResponseMessage().success(res);
    }


    /**
    * 删除用户
    * @param userIds 用户ID,多个用户ID,使用'，'分割
    * @return
    */
    @DeleteMapping("/remove")
    public ResponseMessage remove(String userIds) {
        try {
            int res = userService.delete${className}ByIds(userIds);
        } catch (Exception e) {
            return new ResponseMessage().success(e);
        }
        return new ResponseMessage().success();
    }

}
