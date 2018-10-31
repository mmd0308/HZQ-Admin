package com.hzqing.system.service;

import com.hzqing.system.domain.User;

import java.util.List;

public interface IUserService {
    List<User> selectTableList();

    int insertUser(User user);

    User selectUserById(String userId);

    int deleteUserByIds(String ids) throws Exception;

    int updateUser(User user);

    /**
     * 给用户赋予角色
     * @param userId
     * @param roleIds
     * @return
     */
    int insertUserRole(String userId, String roleIds);

}
