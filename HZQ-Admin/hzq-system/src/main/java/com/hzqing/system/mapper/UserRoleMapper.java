package com.hzqing.system.mapper;

import com.hzqing.system.domain.UserRole;
/**
 *
 * @description 用户角色关系表
 * @author hzqing.com
 */
public interface UserRoleMapper {
    /**
     * 新增 UserRole
     * @param userrole
     * @return
     */
    int insertUserRole(UserRole userrole);

    /**
     * 根据用户的Id删除用户和角色的关系
     * @param userId
     */
    void deleteUserRoleByUserId(String userId);
}
