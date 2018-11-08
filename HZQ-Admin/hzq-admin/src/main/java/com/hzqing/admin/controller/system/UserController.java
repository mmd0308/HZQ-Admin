package com.hzqing.admin.controller.system;

import com.github.pagehelper.PageInfo;
import com.hzqing.common.base.controller.BaseController;
import com.hzqing.common.response.ResponseMessage;
import com.hzqing.system.domain.User;
import com.hzqing.system.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理
 *
 * @author hzqing
 */
@RestController
@RequestMapping("/hzq/system/user")
public class UserController extends BaseController{
    @Autowired
    private IUserService userService;

    /**
     * 获取用户的列表信息，带分页
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public ResponseMessage<PageInfo<User>> list(int pageNum,int pageSize,User user){
        startPage(pageNum,pageSize);
        List<User> users = userService.selectTableList(user);
        return successPage(users);
    }

    /**
     * 获取所有的用户
     * @return
     */
    @GetMapping("/listAll")
    public ResponseMessage<PageInfo<User>> listAll(){
        List<User> users = userService.selectTableList(new User());
        return success(users);
    }


    /**
     * 新增用户信息
     * @param user
     * @return
     */
    @PostMapping("/add")
    public ResponseMessage add(@RequestBody  User user) {
        int res = userService.insertUser(user);
        return success(res);
    }

    /**
     * 根据用户Id修改用户
     * @param userId
     * @return
     */
    @GetMapping("/edit/{userId}")
    public ResponseMessage<User> edit(@PathVariable String userId){
        User user = userService.selectUserById(userId);
        return success(user);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @PutMapping("/edit")
    public ResponseMessage editSave(@RequestBody User user) {
        if (StringUtils.isNotEmpty(user.getUserId()) && User.isAdmin(user.getUserId())) {
            return error("不允许修改系统管理员");
        }
        int res = userService.updateUser(user);
        return success(res);
    }


    /**
     * 删除用户
     * @param userIds 用户ID,多个用户ID,使用'，'分割
     * @return
     */
    @DeleteMapping("/remove")
    public ResponseMessage remove(String userIds) {
        int res = userService.deleteUserByIds(userIds);
        if (res == -1) {
            return error("不允许删除超级管理员");
        }
        return success(res);
    }

    /**
     * 给用户设置角色
     * @param userId 用户id
     * @param roleIds 角色id 使用，分割
     * @return
     */
    @PostMapping("/addUserRole")
    public ResponseMessage addUserRole( String userId, String roleIds) {
        int res = userService.insertUserRole(userId,roleIds);
        return success(res);
    }

}
