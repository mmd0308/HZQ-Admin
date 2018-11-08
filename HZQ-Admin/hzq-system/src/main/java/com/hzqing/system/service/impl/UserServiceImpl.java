package com.hzqing.system.service.impl;

import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.User;
import com.hzqing.system.domain.UserRole;
import com.hzqing.system.mapper.UserMapper;
import com.hzqing.system.mapper.UserRoleMapper;
import com.hzqing.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public int insertUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setUserId(UUIDUtils.get32UUID());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.insertUser(user);
    }

    /**
     * 根据用户Id获取用户信息
     * @param userId
     * @return
     */
    public User selectUserById(String userId) {
        return userMapper.selectUserById(userId);
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
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 给用户赋予角色
     * @param userId
     * @param roleIds
     * @return
     */
    @Override
    public int insertUserRole(String userId, String roleIds) {
        // 删除该用户的所有角色，从新赋予新角色
        userRoleMapper.deleteUserRoleByUserId(userId);
        String[] split = roleIds.split(",");
        int res = 0;
        for (String roleId : split) {
            UserRole userRole = new UserRole();
            userRole.setId(UUIDUtils.get32UUID());
            userRole.setRoleId(roleId);
            userRole.setUserId(userId);
            res = res + userRoleMapper.insertUserRole(userRole);
        }
        return res;
    }
}
