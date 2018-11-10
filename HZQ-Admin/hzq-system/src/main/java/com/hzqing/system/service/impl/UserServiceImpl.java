package com.hzqing.system.service.impl;

import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.User;
import com.hzqing.system.domain.UserRole;
import com.hzqing.system.mapper.UserMapper;
import com.hzqing.system.mapper.UserRoleMapper;
import com.hzqing.system.service.IUserService;
import com.hzqing.system.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<User> selectTableList(User user) {
        return userMapper.selectTableList(user);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    public int insertUser(UserVO user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        String uuid = UUIDUtils.get32UUID();
        user.setUserId(uuid);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        // 新增用户
        int res = userMapper.insertUser(user);
        // 用户绑定角色
        res = res + insertUserRole(uuid,user.getRoleIds());
        return res;
    }

    /**
     * 根据用户Id获取用户信息
     * @param userId
     * @return
     */
    public UserVO selectUserById(String userId) {
        // 根据id获取用户信息
        User user =  userMapper.selectUserById(userId);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        // 根据用户id，获取用户的角色id
        String [] roleIds = userRoleMapper.selectRoleIdsByUserId(userId);
        userVO.setRoleIds(roleIds);
        return userVO;
    }

    /**
     * 根据id批量删除用户
     * @param ids
     * @return
     * @throws Exception
     */
    public int deleteUserByIds(String ids) {
        String[] userIds = ids.split(",");
        for(String userId : userIds) {
            if (User.isAdmin(userId)) {
                return -1;
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    public int updateUser(UserVO user) {
        int res = userMapper.updateUser(user);
        res = res + insertUserRole(user.getUserId(),user.getRoleIds());
        return res;
    }

    /**
     * 给用户赋予角色
     * @param userId
     * @param roleIds
     * @return
     */
    private int insertUserRole(String userId, String[] roleIds) {
        // 删除该用户的所有角色，从新赋予新角色
        userRoleMapper.deleteUserRoleByUserId(userId);
        List<UserRole> userRoles = new ArrayList<>();
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setId(UUIDUtils.get32UUID());
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            userRoles.add(userRole);
        }
        int res =userRoleMapper.insertBatchUserRole(userRoles);
        return res;
    }
}
