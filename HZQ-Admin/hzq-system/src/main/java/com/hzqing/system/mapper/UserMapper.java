package com.hzqing.system.mapper;

import com.hzqing.system.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> selectTableList();

    int insertUser(User user);

    /**
     * 根据用户Id获取用户信息
     * @param userId
     * @return
     */
    User selectUserById(String userId);

    /**
     * 批量删除用户
     * @param userIds 用户Id数组
     * @return
     */
    int deleteUserByIds(String[] userIds);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);
}
