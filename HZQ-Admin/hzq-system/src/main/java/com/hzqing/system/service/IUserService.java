package com.hzqing.system.service;

import com.hzqing.system.domain.User;

import java.util.List;

public interface IUserService {
    /**
     * 根据条件获取所有的用户
     * @param user
     * @return
     */
    List<User> selectTableList(User user);

    int insertUser(User user);

    User selectUserById(String userId);

    int deleteUserByIds(String ids);

    int updateUser(User user);

    /**
     * 给用户赋予角色
     * @param userId
     * @param roleIds
     * @return
     */
    int insertUserRole(String userId, String roleIds);

}
