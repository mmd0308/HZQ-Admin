package com.hzqing.system.service;

import com.hzqing.system.domain.User;
import com.hzqing.system.vo.UserVO;

import java.util.List;

public interface IUserService {
    /**
     * 根据条件获取所有的用户
     * @param user
     * @return
     */
    List<User> selectTableList(User user);

    int insertUser(UserVO user);

    /**
     * 根据用户id获取用户信息
     * @param userId
     * @return
     */
    UserVO selectUserById(String userId);

    int deleteUserByIds(String ids);

    int updateUser(UserVO user);

}
