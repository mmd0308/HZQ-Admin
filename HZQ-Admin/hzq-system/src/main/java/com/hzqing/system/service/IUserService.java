package com.hzqing.system.service;

import com.hzqing.system.domain.User;

import java.util.List;

public interface IUserService {
    List<User> selectTableList();

    int insertUser(User user);

    User selectUserById(String userId);

    int deleteUserByIds(String ids) throws Exception;

    int updateUser(User user);
}
