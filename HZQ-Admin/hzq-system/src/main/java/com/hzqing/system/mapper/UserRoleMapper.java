package com.hzqing.system.mapper;

import com.hzqing.system.domain.UserRole;

import java.util.List;

/**
 *
 * @description 用户角色关系表
 * @author hzqing.com
 */
public interface UserRoleMapper {
    /**
     * 批量新增 UserRole
     * @param userrole
     * @return
     */
    int insertBatchUserRole(List<UserRole> userrole);

    /**
     * 根据用户的Id删除用户和角色的关系
     * @param userId
     */
    void deleteUserRoleByUserId(String userId);

    /**
     * 根据用户id，获取所有的用户角色
     * @param userId
     * @return
     */
    String[] selectRoleIdsByUserId(String userId);
}
