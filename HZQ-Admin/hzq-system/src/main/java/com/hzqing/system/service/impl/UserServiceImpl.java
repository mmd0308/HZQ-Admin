package com.hzqing.system.service.impl;

import com.hzqing.common.util.UUIDUtils;
import com.hzqing.system.domain.User;
import com.hzqing.system.mapper.UserMapper;
import com.hzqing.system.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> selectTableList() {
        return userMapper.selectTableList();
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

    public int deleteUserByIds(String ids) throws Exception {
        String[] userIds = ids.split(",");
        for(String userId : userIds) {
            if (User.isAdmin(userId)) {
                throw new Exception("不允许修改管理员用户");
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }
}
